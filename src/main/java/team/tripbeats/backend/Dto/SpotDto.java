package team.tripbeats.backend.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class SpotDto {
    private Long id;
    private String visitAreaNm;
    private String radNmAddr;
    private int visitAreaTypeCd;
    private String hashtags;
    private String time;
    private String contact;
    private boolean parking;
    private boolean petAccess;
    private String description;
    private double latitude;
    private double longitude;
    private String imageUrl;
}
