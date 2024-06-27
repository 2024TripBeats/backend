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
public class SurveyDto {
    private Long accountId;
    private String email;
    private Long phoneNumber;
    private String gender;
    private String age;

    private String distance;
    private String activityLevel;
    private String scene;
    private int openness;

    private List<String> musicGenres;
    private List<String> musicTags;
    private List<String> travelSpots;
}
