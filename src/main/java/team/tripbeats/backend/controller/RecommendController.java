package team.tripbeats.backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.tripbeats.backend.Dto.FinalInferenceResponseDto;
import team.tripbeats.backend.Dto.InferenceResponseDto;
import team.tripbeats.backend.Dto.RecommendRequestDto;
import team.tripbeats.backend.service.RecommendService;

import java.util.List;

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

    @PostMapping("/getFinalRecommendation")
    public ResponseEntity<FinalInferenceResponseDto> getFinalRecommendation(@RequestBody InferenceResponseDto responseDto, @RequestParam Long accountId) {
        FinalInferenceResponseDto finalResponseDto = recommendService.getFinalRecommendation(accountId, responseDto);
        return ResponseEntity.ok(finalResponseDto);
    }
    @PostMapping("/getAllFinalRecommendation")
    public ResponseEntity<FinalInferenceResponseDto> getAllFinalRecommendation(@RequestBody RecommendRequestDto requestDto) {
        FinalInferenceResponseDto responseDto = recommendService.getAllFinalRecommendation(requestDto);
        return ResponseEntity.ok(responseDto);
    }
}
