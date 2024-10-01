package team.tripbeats.backend.domain.tripRecommand.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import team.tripbeats.backend.domain.tripRecommand.dto.AccPreferDto;
import team.tripbeats.backend.domain.tripRecommand.dto.FinalInferenceResponseDto;
import team.tripbeats.backend.domain.tripRecommand.dto.FinalRecommendationRequestDto;
import team.tripbeats.backend.domain.tripRecommand.dto.InferenceRequestDto;
import team.tripbeats.backend.domain.tripRecommand.dto.InferenceResponseDto;
import team.tripbeats.backend.domain.tripRecommand.dto.RecommendRequestDto;
import team.tripbeats.backend.domain.tripRecommand.dto.RestSurveyDto;
import team.tripbeats.backend.domain.tripRecommand.dto.UserFeaturesDto;
import team.tripbeats.backend.entity.Account;
import team.tripbeats.backend.repository.AccountRepository;

import java.util.Collections;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RecommendService {

    private final AccountRepository accountRepository;
    private final RestTemplate restTemplate;

    @Transactional
    public InferenceResponseDto getRecommendations(RecommendRequestDto recommendRequestDto) {
        Long accountId = recommendRequestDto.getAccountId();

        // Account 정보 불러오기
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid account ID: " + accountId));

        // InferenceRequestDto 생성
        InferenceRequestDto requestDto = InferenceRequestDto.builder()
                // rest_survey 정보 (설문에서 받은 값들)
                .rest_survey(RestSurveyDto.builder()
                        .restaurant(recommendRequestDto.getRestaurant())
                        .requiredRestText(recommendRequestDto.getRequiredRestText())
                        .cafe(recommendRequestDto.getCafe())
                        .build())

                // acc_prefer 정보 (설문에서 받은 값들)
                .acc_prefer(AccPreferDto.builder()
                        .accomodation(recommendRequestDto.getAccomodation())
                        .requiredAccomText(recommendRequestDto.getRequiredAccomText())
                        .accompriority(recommendRequestDto.getAccompriority())
                        .build())

                // user_features 정보 (유저 레포에서 받은 값들)
                .user_features(UserFeaturesDto.builder()
                        .gender(Collections.singletonList(Integer.parseInt(account.getGender())))  // 유저 gender
                        .age_grp(Collections.singletonList(Integer.parseInt(account.getAge())))    // 유저 age
                        .travel_styl_1(Collections.singletonList(Integer.parseInt(account.getScene())))    // 유저 scene
                        .travel_styl_2(Collections.singletonList(Integer.parseInt(account.getDistance()))) // 유저 distance
                        .travel_styl_3(Collections.singletonList(account.getOpenness())) // 유저 openness
                        .travel_styl_4(Collections.singletonList(Integer.parseInt(account.getActivityLevel()))) // 유저 activityLevel
                        .build())

                // 기타 정보 (설문 및 유저 레포에서 받아온 값들)
                .user_prefer(recommendRequestDto.getTravelCategory()) // 설문 travelCategory
                .input_order(
                        account.getTravelSpots().stream()
                                .map(travelSpot -> Integer.parseInt(travelSpot.getSpotName()))  // 각 TravelSpot 객체에서 spotName을 추출
                                .collect(Collectors.toList())
                )
                .user_trip_days((recommendRequestDto.getEndDate().getDayOfYear() - recommendRequestDto.getStartDate().getDayOfYear()) + 1) // 여행 기간 계산
                .user_difficulty(recommendRequestDto.getIntensity()) // 설문 intensity
                .user_openness(account.getOpenness()) // 유저 openness
                .start_time(recommendRequestDto.getDepartureTime()) // 설문 departureTime
                .build();
        System.out.println(requestDto);

        // 추천 API 호출
        String inferenceUrl = "http://localhost:8001/recommend";
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

        String finalInferenceUrl = "http://localhost:8002/music_recommend";  // 다른 인퍼런스 서버 URL
        return restTemplate.postForObject(finalInferenceUrl, finalRequestDto, FinalInferenceResponseDto.class);
    }
    @Transactional
    public FinalInferenceResponseDto getAllRecommendation(RecommendRequestDto recommendRequestDto) {
        InferenceResponseDto responseDto = getRecommendations(recommendRequestDto);
        return getMusicRecommendation(recommendRequestDto.getAccountId(), responseDto);
    }
}