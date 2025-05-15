package com.example.co_ngu_hanh_game.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlayerProfileDTO {
    private String username;
    private int wins;
    private float winrate;
    private int eloPoints;
    private int currentStars;
}
