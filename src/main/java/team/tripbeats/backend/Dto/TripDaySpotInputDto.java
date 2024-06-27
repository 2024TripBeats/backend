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
    private Integer spotId;
    private Integer visitOrder;
    private Integer musicId;  // 입력용 필드
}
