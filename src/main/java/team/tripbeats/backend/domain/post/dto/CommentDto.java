package team.tripbeats.backend.domain.post.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentDto {
    private Long id;            // 댓글 ID
    private String content;      // 댓글 내용
    private String accountName;  // 작성자 이름 (kakaoName)
}