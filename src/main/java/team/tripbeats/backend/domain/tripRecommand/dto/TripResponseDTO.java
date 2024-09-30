package team.tripbeats.backend.domain.tripRecommand.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripResponseDTO {

    private Long tripId;         // Trip의 ID
    private Long accountId;      // Trip을 소유한 사용자의 ID
    private String tripName;     // Trip 이름
    private Map<String, Object> tripData; // Trip에 관련된 JSON 데이터
}
