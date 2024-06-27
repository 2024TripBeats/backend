package team.tripbeats.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.tripbeats.backend.Dto.SurveyDto;
import team.tripbeats.backend.entity.Account;
import team.tripbeats.backend.service.SurveyService;

@RestController
@RequestMapping("/saveSurvey")
public class SurveyController {

    private final SurveyService surveyService;

    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @PostMapping
    public ResponseEntity<Account> saveSurvey(@RequestBody SurveyDto surveyDto) {
        Account savedAccount = surveyService.saveSurvey(surveyDto);
        return ResponseEntity.ok(savedAccount);
    }
}
