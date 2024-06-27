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
public class TripDayInputDto {
    private int dayNumber;
    private List<TripDaySpotInputDto> tripDaySpots;
}
