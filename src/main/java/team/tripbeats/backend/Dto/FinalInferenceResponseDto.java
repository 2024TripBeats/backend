package team.tripbeats.backend.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FinalInferenceResponseDto {
    private List<FinalDayRecommendationDto> recommendations;
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class FinalDayRecommendationDto {
    private int dayNumber;
    private List<FinalRecommendationCandidateDto> candidates;
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class FinalRecommendationCandidateDto {
    private List<FinalPlaceMusicPairDto> itinerary;
    private List<FinalTravelSegmentDto> travelSegments;
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class FinalPlaceMusicPairDto {
    private String placeId;
    private String placeName;
    private int duration;
    private String musicId;
    private String musicName;
    private String musicArtist;
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class FinalTravelSegmentDto {
    private float distance;
}
