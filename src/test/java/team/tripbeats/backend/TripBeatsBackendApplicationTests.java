package team.tripbeats.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(
        properties = {
                "KAKAO_CLIENT_ID=example",
                "KAKAO_REDIRECT_URI=example",
                "KAKAO_CLIENT_SECRET=example"
        }
)
class TripBeatsBackendApplicationTests {

    @Test
    void contextLoads() {
    }

}
