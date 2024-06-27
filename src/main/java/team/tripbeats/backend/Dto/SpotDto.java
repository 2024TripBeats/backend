package team.tripbeats.backend.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpotDto {
    private Integer id;
    private String imageUrl;
    private String placeName;
    private String location;
    private String description;
    private MusicDto music;
    private CurationDto curation;
}
