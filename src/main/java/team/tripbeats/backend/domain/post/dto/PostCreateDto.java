package team.tripbeats.backend.domain.post.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostCreateDto {
    private Long accountId;
    private String title;
    private String content;
    private String category;  // 카테고리 추가
    private String location;  // 장소 추가
    private String schedule;  // 일정 추가
    private String image;     // 이미지 추가 (Base64 인코딩된 값)
}
