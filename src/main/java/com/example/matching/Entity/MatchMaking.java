package com.example.matching.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "matchmaking")
public class MatchMaking {
//    CREATE TABLE IF NOT EXISTS matchmaking (
//            queue_id INT AUTO_INCREMENT PRIMARY KEY,
//            user_id INT,
//            entered_at DATETIME DEFAULT CURRENT_TIMESTAMP,
//            FOREIGN KEY (user_id) REFERENCES users(user_id)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer queue_id;

    private LocalDateTime entered_at;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}


