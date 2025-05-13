package com.example.levels.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LevelsDTO {
    private Integer level_id;
    private String level_name;
    private Integer required_matches;
    private Float required_winrate;
    private Integer reward_stars;
    private Integer stars_bet;
}
