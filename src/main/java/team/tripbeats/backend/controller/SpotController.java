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

@RestController
@RequestMapping("/spots")
@AllArgsConstructor
public class SpotController {

    private final SpotService spotService;

    @GetMapping("/{visitAreaNm}")
    public ResponseEntity<SpotDto> getSpotByVisitAreaNm(@PathVariable String visitAreaNm) {
        Spot spot = spotService.getSpotByVisitAreaNm(visitAreaNm);
        return spot != null ? ResponseEntity.ok(spotService.convertSpotToDto(spot)) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{visitAreaNm}/image")
    public ResponseEntity<String> getSpotImageByVisitAreaNm(@PathVariable String visitAreaNm) {
        Spot spot = spotService.getSpotByVisitAreaNm(visitAreaNm);
        return spot != null ? ResponseEntity.ok(spot.getImageUrl()) : ResponseEntity.notFound().build();
    }
}
