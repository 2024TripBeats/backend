package team.tripbeats.backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.tripbeats.backend.Dto.SpotDto;
import team.tripbeats.backend.entity.Spot;
import team.tripbeats.backend.service.SpotService;

import java.util.Optional;

@RestController
@RequestMapping("/spots")
@AllArgsConstructor
public class SpotController {

    private final SpotService spotService;

    @GetMapping("/{id}")
    public ResponseEntity<SpotDto> getSpotById(@PathVariable Long id) {
        Optional<Spot> spot = spotService.getSpotById(id);
        return spot.map(value -> ResponseEntity.ok(spotService.convertSpotToDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
