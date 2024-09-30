package team.tripbeats.backend.domain.tripRecommand.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripRequestDTO {

    private Long accountId;
    private String tripName;

    // tripData 데이터를 JSON 형식으로 받음
    private Map<String, Object> tripData;
}
