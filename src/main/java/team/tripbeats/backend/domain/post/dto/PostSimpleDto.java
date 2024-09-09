package team.tripbeats.backend.domain.post.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostSimpleDto {
    private Long id;
    private String title;
}
