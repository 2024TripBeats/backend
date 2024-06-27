package team.tripbeats.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.tripbeats.backend.entity.Account;
import team.tripbeats.backend.repository.AccountRepository;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Account findAccountById(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found with id: " + accountId));
    }

    public boolean isSurveyDone(Long accountId) {
        return accountRepository.findById(accountId)
                .map(Account::isDoneSurvey)
                .orElse(false);
    }
}
