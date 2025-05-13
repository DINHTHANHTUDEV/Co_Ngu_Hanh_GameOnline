package com.example.levels.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "player_levels")
public class Levels {
//    CREATE TABLE player_levels (
//            level_id INT IDENTITY(1,1) PRIMARY KEY,
//    level_name NVARCHAR(50),
//    required_matches INT,
//    required_winrate FLOAT,
//    reward_stars INT,
//    stars_bet INT
//);
//    GO
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer level_id;
    private String level_name;
    private Integer required_matches;
    private Float required_winrate;
    private Integer reward_stars;
    private Integer stars_bet;
}
