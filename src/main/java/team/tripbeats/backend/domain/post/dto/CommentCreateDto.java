package team.tripbeats.backend.domain.post.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentCreateDto {
    private Long postId;      // 게시글 ID
    private Long accountId;   // 작성자 ID
    private String content;   // 댓글 내용
}
