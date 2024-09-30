package team.tripbeats.backend.domain.FlightFare.dto;

import lombok.Data;

@Data
public class FareRequestDto {
    private String startAirport;
    private String departureTime;
}
