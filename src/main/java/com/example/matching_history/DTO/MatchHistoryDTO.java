package com.example.matching_history.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Service
public class MatchHistoryDTO {
    private String player1_username;
    private String player2_username;
    private String winner_username;
    private String match_type;
    private Integer stars_bet;
    private Integer stars_change;
    private Integer elo_change;
    private String moves_json;
    private LocalDateTime played_at;

}
