package team.tripbeats.backend.domain.FlightFare.dto;

import lombok.Data;

@Data
public class RoundFareRequestDto {
    private String startDate;
    private String endDate;
    private String startAirport;
    private String departureTime;
    private String returnTime;
    private String endAirport;
}
