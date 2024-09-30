package team.tripbeats.backend.domain.FlightFare.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.tripbeats.backend.domain.FlightFare.dto.FareRequestDto;
import team.tripbeats.backend.domain.FlightFare.dto.RoundFareRequestDto;
import team.tripbeats.backend.domain.FlightFare.service.FlightFareService;

import java.util.Map;

@RestController
@RequestMapping("/flights")
@AllArgsConstructor
public class FlightFareController {

    private final FlightFareService flightFareService;

    // 특정 출발 공항과 시간대의 날짜별 항공편 가격 조회
    @PostMapping("/fare")
    public Map<String, Integer> getFareByStartAirportAndTime(@RequestBody FareRequestDto fareRequestDto) {
        return flightFareService.getFareByStartAirportAndTime(fareRequestDto);
    }

    // 왕복 항공편 가격 조회 (출발일과 돌아오는 날)
    @PostMapping("/round-trip-fare")
    public Integer getRoundTripFare(@RequestBody RoundFareRequestDto roundFareRequestDto) {
        return flightFareService.getRoundTripFare(roundFareRequestDto);
    }
}
