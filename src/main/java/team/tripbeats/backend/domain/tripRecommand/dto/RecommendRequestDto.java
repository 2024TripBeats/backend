package team.tripbeats.backend.domain.tripRecommand.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecommendRequestDto {
    private Long accountId;
    private String destination;
    private String tripName;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Integer> intensity;

    // 설문으로 받아온 필드들
    private List<String> accomodation; // 숙소 선호사항
    private String requiredAccomText; // 필수 숙소 항목
    private String accompriority; // 숙소 우선순위
    private List<String> restaurant; // 식당 선호사항
    private String requiredRestText; // 필수 식당 항목
    private List<String> cafe; // 카페 선호사항
    private List<String> travelCategory; // 여행 카테고리
    private String stopwords; // 피하고 싶은 요소
    private String requirewords; // 꼭 가고 싶은 곳
    private String startAirport; // 출발 공항
    private String departureTime; // 출발 시간
    private String endAirport; // 도착 공항
    private String returnTime; // 귀환 시간
}
