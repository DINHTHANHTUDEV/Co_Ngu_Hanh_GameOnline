package com.example.Ranking.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RankingDTO {
    private Integer user_id;
    private String username;
    private Integer rank_points;
    private Integer month;
    private Integer year;
}
