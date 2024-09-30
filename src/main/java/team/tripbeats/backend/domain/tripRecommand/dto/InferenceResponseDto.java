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
    private List<DayRecommendationDto> recommendations;
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class DayRecommendationDto {
    private int dayNumber;
    private List<RecommendationCandidateDto> candidates;
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class RecommendationCandidateDto {
    private List<PlaceDto> itinerary;
    private List<TravelSegmentDto> travelSegments;
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class PlaceDto {
    private String placeId;
    private String placeName;
    private int duration;
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class TravelSegmentDto {
    private float distance;
}
