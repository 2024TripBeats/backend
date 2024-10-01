package team.tripbeats.backend.domain.tripRecommand.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InferenceRequestDto {

    // 설문에서 받은 rest_survey 정보
    private RestSurveyDto rest_survey;

    // 설문에서 받은 acc_prefer 정보
    private AccPreferDto acc_prefer;

    // 유저 레포에서 받은 user_features 정보
    private UserFeaturesDto user_features;

    // 설문에서 받은 기타 정보
    private List<String> user_prefer; // 설문 travelCategory
    private List<Integer> input_order; // 유저 travelSpots
    private Integer user_trip_days; // 설문 startDate와 endDate로 계산
    private List<Integer> user_difficulty; // 설문 intensity
    private Integer user_openness; // 유저 openness
    private String start_time; // 설문 departureTime
}

