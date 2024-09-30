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
public class InferenceRequestDto {
    private Long accountId;
    private String destination;
    private Integer period;
    private List<Integer> intensity;
    private String stopwords;
    private String requirewords;

    private String gender;
    private String age;
    private String distance;
    private String activityLevel;
    private String scene;
    private int openness;
    private List<String> musicGenres;
    private int genreOpenness;
    private List<String> musicTags;
    private int tagOpenness;
    private List<Long> travelSpots;
}
