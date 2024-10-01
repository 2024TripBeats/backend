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
public class FinalInferenceResponseDto {
    private List<FinalDayRecommendationDto> recommendations;
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class FinalDayRecommendationDto {
    private int dayNumber;
    private List<FinalRecommendationCandidateDto> itinerary;
    private List<FinalTravelSegmentDto> travelSegments;
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class FinalRecommendationCandidateDto {
    private List<FinalPlaceMusicPairDto> places;
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class FinalPlaceMusicPairDto {
    private String placeId;
    private String placeName;
    private String category;  // 카테고리 필드를 추가
    private int duration;
    private int order;
    private int newOrder;  // 새로운 순서 필드 추가
    private String timeOfDay;  // 아침, 오후, 밤 등의 시간대 필드 추가
    private boolean musicBool;  // 음악이 있는지 여부를 나타내는 필드
    private String musicId;
    private String musicName;
    private String musicArtist;
    private String spotifyId;
    private int price;  // 장소의 가격 필드 추가
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class FinalTravelSegmentDto {
    private float distance;
}
