package team.tripbeats.backend.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.tripbeats.backend.Dto.SpotDto;
import team.tripbeats.backend.entity.Spot;
import team.tripbeats.backend.service.SpotService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/spots")
@RequiredArgsConstructor
public class SpotController {

    private final SpotService spotService;

    @GetMapping
    public ResponseEntity<List<SpotDto>> getAllSpots() {
        List<Spot> spots = spotService.getAllSpots();
        List<SpotDto> spotDtos = spots.stream()
                .map(spotService::convertSpotToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(spotDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpotDto> getSpotById(@PathVariable Long id) {
        Spot spot = spotService.getSpotById(id);
        if (spot != null) {
            return ResponseEntity.ok(spotService.convertSpotToDto(spot));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
