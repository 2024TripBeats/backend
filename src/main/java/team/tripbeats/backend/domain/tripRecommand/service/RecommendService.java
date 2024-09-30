package team.tripbeats.backend.domain.tripRecommand.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import team.tripbeats.backend.domain.tripRecommand.dto.FinalInferenceResponseDto;
import team.tripbeats.backend.domain.tripRecommand.dto.FinalRecommendationRequestDto;
import team.tripbeats.backend.domain.tripRecommand.dto.InferenceRequestDto;
import team.tripbeats.backend.domain.tripRecommand.dto.InferenceResponseDto;
import team.tripbeats.backend.domain.tripRecommand.dto.RecommendRequestDto;
import team.tripbeats.backend.entity.Account;
import team.tripbeats.backend.repository.AccountRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RecommendService {

    private final AccountRepository accountRepository;
    private final RestTemplate restTemplate;

    @Transactional
    public InferenceResponseDto getRecommendations(RecommendRequestDto recommendRequestDto) {
        Long accountId = recommendRequestDto.getAccountId();
        String destination = recommendRequestDto.getDestination();
        Integer period = recommendRequestDto.getPeriod();
        List<Integer> intensity = recommendRequestDto.getIntensity();
        String stopwords = recommendRequestDto.getStopwords();
        String requirewords = recommendRequestDto.getRequirewords();

            Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid account ID: " + accountId));

        InferenceRequestDto requestDto = InferenceRequestDto.builder()
                .accountId(accountId)
                .destination(destination)
                .period(period)
                .intensity(intensity)
                .stopwords(stopwords)
                .requirewords(requirewords)
                .gender(account.getGender())
                .age(account.getAge())
                .distance(account.getDistance())
                .activityLevel(account.getActivityLevel())
                .scene(account.getScene())
                .openness(account.getOpenness())
                .musicGenres(account.getMusicGenres().stream().map(genre -> genre.getName()).collect(Collectors.toList()))
                .genreOpenness(account.getGenreOpenness())
                .musicTags(account.getMusicTags().stream().map(tag -> tag.getName()).collect(Collectors.toList()))
                .tagOpenness(account.getTagOpenness())
                .travelSpots(account.getTravelSpots().stream().map(travelSpot -> travelSpot.getId()).collect(Collectors.toList()))
                .build();

        String inferenceUrl = "http://localhost:8000/recommend";  // 로컬 호스트의 8000번 포트로 설정
        return restTemplate.postForObject(inferenceUrl, requestDto, InferenceResponseDto.class);
    }

    public FinalInferenceResponseDto getMusicRecommendation(Long accountId, InferenceResponseDto responseDto) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid account ID: " + accountId));

        FinalRecommendationRequestDto finalRequestDto = FinalRecommendationRequestDto.builder()
                .recommendations(responseDto.getRecommendations())
                .musicGenres(account.getMusicGenres().stream().map(genre -> genre.getName()).collect(Collectors.toList()))
                .genreOpenness(account.getGenreOpenness())
                .musicTags(account.getMusicTags().stream().map(tag -> tag.getName()).collect(Collectors.toList()))
                .tagOpenness(account.getTagOpenness())
                .build();

        String finalInferenceUrl = "http://localhost:8001/music_recommend";  // 다른 인퍼런스 서버 URL
        return restTemplate.postForObject(finalInferenceUrl, finalRequestDto, FinalInferenceResponseDto.class);
    }
    @Transactional
    public FinalInferenceResponseDto getAllRecommendation(RecommendRequestDto recommendRequestDto) {
        InferenceResponseDto responseDto = getRecommendations(recommendRequestDto);
        return getMusicRecommendation(recommendRequestDto.getAccountId(), responseDto);
    }
}