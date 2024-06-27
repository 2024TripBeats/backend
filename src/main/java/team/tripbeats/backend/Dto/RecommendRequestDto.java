package team.tripbeats.backend.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecommendRequestDto {
    private Long accountId;
    private String destination;
    private Integer period;
    private List<Integer> intensity;
    private String stopwords;
    private String requirewords;
}
