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
    private Long id;
    private String visitAreaNm;
    private String radNmAddr;
    private Integer visitAreaTypeCd;
    private String hashtags;
    private String time;
    private String 연락처;
    private String 주차;
    private String 동물출입;
    private String 소개글;
    private Double lat;
    private Double lng;
    private String imageUrl;
}
