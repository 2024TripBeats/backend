package team.tripbeats.backend.domain.tripRecommand.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.tripbeats.backend.domain.tripRecommand.dto.FinalInferenceResponseDto;
import team.tripbeats.backend.domain.tripRecommand.dto.InferenceResponseDto;
import team.tripbeats.backend.domain.tripRecommand.dto.RecommendRequestDto;
import team.tripbeats.backend.domain.tripRecommand.service.RecommendService;

@RestController
@RequestMapping("/recommend")
@AllArgsConstructor
public class RecommendController {

    private final RecommendService recommendService;

    @PostMapping("/getRecommendations")
    public ResponseEntity<InferenceResponseDto> getRecommendations(@RequestBody RecommendRequestDto requestDto) {
        InferenceResponseDto responseDto = recommendService.getRecommendations(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/getMusicRecommendation")
    public ResponseEntity<FinalInferenceResponseDto> getMusicRecommendation(@RequestBody InferenceResponseDto responseDto, @RequestParam Long accountId) {
        FinalInferenceResponseDto finalResponseDto = recommendService.getMusicRecommendation(accountId, responseDto);
        return ResponseEntity.ok(finalResponseDto);
    }
    @PostMapping("/getAllRecommendation")
    public ResponseEntity<FinalInferenceResponseDto> getAllRecommendation(@RequestBody RecommendRequestDto requestDto) {
        FinalInferenceResponseDto responseDto = recommendService.getAllRecommendation(requestDto);
        return ResponseEntity.ok(responseDto);
    }
}
