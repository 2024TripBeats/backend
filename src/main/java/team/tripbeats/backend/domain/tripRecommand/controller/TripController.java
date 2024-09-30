package team.tripbeats.backend.domain.tripRecommand.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import team.tripbeats.backend.domain.tripRecommand.dto.TripRequestDTO;
import team.tripbeats.backend.domain.tripRecommand.dto.TripResponseDTO;
import team.tripbeats.backend.domain.tripRecommand.service.TripService;

import java.util.List;

@RestController
@RequestMapping("/trips")
@AllArgsConstructor
public class TripController {

    private final TripService tripService;

    // Trip 생성
    @PostMapping("/")
    public void createTrip(@RequestBody TripRequestDTO tripRequestDTO) {
        tripService.saveTrip(tripRequestDTO);
    }

    // Trip 조회
    @GetMapping("/{tripId}")
    public TripResponseDTO getTripById(@PathVariable Long tripId) {
        return tripService.getTripById(tripId);
    }

    @GetMapping("/account/{accountId}/trip-ids")
    public List<Long> getTripIdsByAccountId(@PathVariable Long accountId) {
        return tripService.getTripIdsByAccountId(accountId);
    }
}
