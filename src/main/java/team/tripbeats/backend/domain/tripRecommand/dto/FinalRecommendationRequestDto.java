package team.tripbeats.backend.domain.tripRecommand.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FinalRecommendationRequestDto {
    private List<DayRecommendationDto> recommendations;
    private List<String> musicGenres;
    private int genreOpenness;
    private List<String> musicTags;
    private int tagOpenness;
}
