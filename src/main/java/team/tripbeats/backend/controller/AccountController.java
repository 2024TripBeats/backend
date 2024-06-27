package team.tripbeats.backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.tripbeats.backend.Dto.SurveyDto;
import team.tripbeats.backend.entity.Account;
import team.tripbeats.backend.service.AccountService;
import team.tripbeats.backend.service.SurveyService;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final SurveyService surveyService;

    @GetMapping("/{accountId}/survey")
    public ResponseEntity<SurveyDto> getSurveyByAccountId(@PathVariable Long accountId) {
        Account account = accountService.findAccountById(accountId);
        SurveyDto surveyDto = surveyService.convertToDto(account);
        return ResponseEntity.ok(surveyDto);
    }

    @GetMapping("/{accountId}/doneSurvey")
    public ResponseEntity<Boolean> getDoneSurveyByAccountId(@PathVariable Long accountId) {
        boolean isDone = accountService.isSurveyDone(accountId);
        return ResponseEntity.ok(isDone);
    }
}
