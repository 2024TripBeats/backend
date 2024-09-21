package team.tripbeats.backend.domain.post.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class PostDetailDto {
    private Long postId;
    private String title;
    private String content;
    private String category;
    private String location;
    private String schedule;
    private Long userId;        // 작성자 ID
    private String kakaoName;   // 작성자 이름
    private String image;
    private LocalDateTime timestamp;

    private int totalCount;            // 댓글 총 개수
    private List<CommentDto> commentList; // 댓글 리스트
}
