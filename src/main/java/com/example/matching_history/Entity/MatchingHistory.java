package com.example.matching_history.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="match_history")
public class MatchingHistory {
//    CREATE TABLE match_history (
//            match_id INT IDENTITY(1,1) PRIMARY KEY,
//    player1_id INT NOT NULL,
//    player2_id INT NOT NULL,
//    winner_id INT NOT NULL,
//    match_type NVARCHAR(20) CHECK (match_type IN ('RANK', 'CASUAL')) NOT NULL,
//    stars_bet INT DEFAULT 0,
//    stars_change INT DEFAULT 0,
//    elo_change INT DEFAULT 0,
//    moves_json NVARCHAR(MAX),
//    played_at DATETIME DEFAULT GETDATE(),
//    FOREIGN KEY (player1_id) REFERENCES users(user_id),
//    FOREIGN KEY (player2_id) REFERENCES users(user_id),
//    FOREIGN KEY (winner_id) REFERENCES users(user_id)
//            );
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer match_id;
    private String match_type;
    private Integer stars_bet;
    private Integer stars_change;
    private Integer elo_change;
    private String moves_json;
    private LocalDateTime played_at;
    @ManyToOne
    @JoinColumn(name = "player1_id",referencedColumnName = "user_id")
    private User player1;

    @ManyToOne
    @JoinColumn(name = "player2_id",referencedColumnName = "user_id")
    private User player2;

    @ManyToOne
    @JoinColumn(name = "winner_id",referencedColumnName = "user_id")
    private User winner;
}
