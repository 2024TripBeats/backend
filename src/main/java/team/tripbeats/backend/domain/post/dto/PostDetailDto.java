package team.tripbeats.backend.domain.post.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostDetailDto {
    private Long id;
    private String title;
    private String content;
    private String accountName;  // 작성자의 이름 (kakaoName)
}