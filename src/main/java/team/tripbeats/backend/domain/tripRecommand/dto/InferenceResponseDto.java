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
public class InferenceResponseDto {
    private List<CandidateRecommendationDto> recommendations;  // candidates를 리스트로 수정
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class CandidateRecommendationDto {
    private int candidates;  // candidates 필드 추가
    private List<DayItineraryDto> itinerary;  // DayItineraryDto로 변경
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class DayItineraryDto {
    private int dayNumber;  // dayNumber 추가
    private List<PlaceDto> places;  // places 리스트로 수정
    private List<TravelSegmentDto> travelSegments;  // travelSegments 추가
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class PlaceDto {
    private String placeId;
    private String placeName;
    private String category;  // category 필드 추가
    private int duration;
    private int order;  // order 필드 추가
    private int price;  // price 필드 추가
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class TravelSegmentDto {
    private float distance;
}
