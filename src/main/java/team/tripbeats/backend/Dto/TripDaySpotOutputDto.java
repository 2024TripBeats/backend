package team.tripbeats.backend.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripDaySpotOutputDto {
    private Long spotId;
    private Integer visitOrder;
    private MusicDto musicDto; // 출력용 필드
}
