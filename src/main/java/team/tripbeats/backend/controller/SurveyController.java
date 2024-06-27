package team.tripbeats.backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.tripbeats.backend.Dto.SurveyDto;
import team.tripbeats.backend.entity.Account;
import team.tripbeats.backend.service.SurveyService;

@RestController
@RequestMapping("/surveys")
@AllArgsConstructor
public class SurveyController {

    private final SurveyService surveyService;

    @PostMapping
    public ResponseEntity<SurveyDto> createOrUpdateSurvey(@RequestBody SurveyDto surveyDto) {
        Account savedAccount = surveyService.saveSurvey(surveyDto);
        SurveyDto responseDto = surveyService.convertToDto(savedAccount);
        return ResponseEntity.ok(responseDto);
    }
}
