package team.tripbeats.backend.Dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TripDaySpotDto {
    private Integer spotId;
    private Integer visitOrder;
}
