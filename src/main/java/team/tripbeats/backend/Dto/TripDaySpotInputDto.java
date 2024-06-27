package team.tripbeats.backend.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripDaySpotInputDto {
    private Long spotId;
    private Integer visitOrder;
    private Long musicId;  // 입력용 필드
}
