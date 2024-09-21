package team.tripbeats.backend.domain.post.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PostSimpleDto {
    private Long postId;           // 게시글 ID
    private String title;          // 게시글 제목
    private String category;       // 카테고리
    private String location;       // 장소
    private String schedule;       // 일정
    private LocalDateTime timestamp; // 작성 시간
    private String image;          // Base64 인코딩된 이미지 추가
    private int comments;          // 댓글 수 추가
}
