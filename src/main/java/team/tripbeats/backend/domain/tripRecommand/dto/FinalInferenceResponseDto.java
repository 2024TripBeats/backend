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
    private List<FinalCandidateRecommendationDto> recommendations;  // candidates 리스트로 수정
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class FinalCandidateRecommendationDto {
    private int candidates;  // 후보 수 필드 추가
    private List<FinalDayItineraryDto> itinerary;  // 하루 일정 리스트
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class FinalDayItineraryDto {
    private int dayNumber;  // dayNumber 추가
    private List<FinalPlaceDto> places;  // 방문 장소 리스트
    private List<FinalTravelSegmentDto> travelSegments;  // 장소 간 이동 구간 리스트
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class FinalPlaceDto {
    private String placeId;
    private String placeName;
    private String category;  // 카테고리 필드 추가 (식당, 박물관 등)
    private int duration;
    private int order;  // 방문 순서
    private int newOrder;  // 새로운 순서 필드 추가
    private String timeOfDay;  // 시간대 (아침, 오후, 밤 등)
    private boolean musicBool;  // 음악 여부
    private String musicId;  // 음악 ID
    private String musicName;  // 음악 이름
    private String musicArtist;  // 음악 아티스트 이름
    private String spotifyId;  // Spotify ID
    private int price;  // 장소의 가격 필드 추가
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class FinalTravelSegmentDto {
    private float distance;  // 장소 간 거리
}
