package team.tripbeats.backend.domain.post.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentDto {
    private Long commentId;     // 댓글 ID
    private Long userId;        // 댓글 작성자 ID
    private String kakaoName;   // 댓글 작성자 이름
    private String content;     // 댓글 내용
    private LocalDateTime timestamp;   // 댓글 작성 시간
}
