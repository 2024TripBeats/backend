package team.tripbeats.backend.Dto;

import lombok.Data;

import team.tripbeats.backend.entity.Account;

@Data
public class LoginResponseDto {
    private boolean loginSuccess;
    private Account account;
}
