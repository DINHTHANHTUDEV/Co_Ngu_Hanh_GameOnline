package com.example.co_ngu_hanh_game.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "player_profiles")
public class PlayerProfiles {
    @Id
    private Integer userId;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId", insertable = false, updatable = false)
    private Users user;

    private int totalMatches;
    private int wins;
    private float winrate;
    private int currentStars;
    private int eloPoints;
}
