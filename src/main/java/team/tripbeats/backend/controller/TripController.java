package team.tripbeats.backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.tripbeats.backend.Dto.TripInputDto;
import team.tripbeats.backend.Dto.TripOutputDto;
import team.tripbeats.backend.entity.Trip;
import team.tripbeats.backend.service.TripService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trips")
@AllArgsConstructor
public class TripController {

    private final TripService tripService;

    @GetMapping("/{id}")
    public ResponseEntity<TripOutputDto> getTripById(@PathVariable Integer id) {
        Optional<Trip> trip = tripService.getTripById(id);
        return trip.map(value -> ResponseEntity.ok(tripService.convertToDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TripOutputDto> createTrip(@RequestBody TripInputDto tripInputDto) {
        Trip savedTrip = tripService.saveTrip(tripInputDto);
        return ResponseEntity.ok(tripService.convertToDto(savedTrip));
    }

    @GetMapping("/account/{accountId}/tripIds")
    public ResponseEntity<List<Integer>> getTripIdsByAccountId(@PathVariable Long accountId) {
        List<Integer> tripIds = tripService.getTripIdsByAccountId(accountId);
        return ResponseEntity.ok(tripIds);
    }
}