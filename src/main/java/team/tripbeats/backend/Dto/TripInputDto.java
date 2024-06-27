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
public class TripInputDto {
    private String accountId;
    private String tripName;
    private Integer period;
    private List<TripDayInputDto> tripDays;
}
