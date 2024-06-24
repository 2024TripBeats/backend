package team.tripbeats.backend.Dto;

import lombok.Data;

@Data
public class KakaoAccountDto {
    private Long id;
    private KakaoAccount kakao_account;

    @Data
    public static class KakaoAccount {
        private String email;
        private Profile profile;

        @Data
        public static class Profile {
            private String nickname;
        }
    }
}
