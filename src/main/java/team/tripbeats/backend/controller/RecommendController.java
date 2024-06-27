package team.tripbeats.backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.tripbeats.backend.Dto.InferenceResponseDto;
import team.tripbeats.backend.service.RecommendService;

import java.util.List;

@RestController
@RequestMapping("/recommend")
@AllArgsConstructor
public class RecommendController {

    private final RecommendService recommendService;

    @PostMapping
    public ResponseEntity<InferenceResponseDto> getRecommendations(@RequestParam Long accountId,
                                                                   @RequestParam String destination,
                                                                   @RequestParam Integer period,
                                                                   @RequestParam List<Integer> intensity,
                                                                   @RequestParam String stopwords,
                                                                   @RequestParam String requirewords) {
        InferenceResponseDto responseDto = recommendService.getRecommendations(accountId, destination, period, intensity, stopwords, requirewords);
        return ResponseEntity.ok(responseDto);
    }
}
