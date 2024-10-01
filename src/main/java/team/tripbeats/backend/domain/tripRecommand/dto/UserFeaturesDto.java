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
public class UserFeaturesDto {
    private List<Integer> gender;
    private List<Integer> age_grp;
    private List<Integer> travel_styl_1;
    private List<Integer> travel_styl_2;
    private List<Integer> travel_styl_3;
    private List<Integer> travel_styl_4;
}
