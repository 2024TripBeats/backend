package team.tripbeats.backend.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import team.tripbeats.backend.Dto.InferenceRequestDto;
import team.tripbeats.backend.Dto.InferenceResponseDto;
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
    public InferenceResponseDto getRecommendations(Long accountId, String destination, Integer period, List<Integer> intensity, String stopwords, String requirewords) {
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
}