package team.tripbeats.backend.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SpotDto {
    private String spotId;
    private String visitAreaNm;
    private String radNmAddr;
    private String visitAreaTypeCd;
    private String hashtags;
    private String time;
    private String contact;
    private String info;
    private String fare;
    private String description;
    private String imageUrl;
}
