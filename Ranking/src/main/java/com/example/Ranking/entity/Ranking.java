package com.example.Ranking.entity;

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
@Table(name = "ranking_monthly")
public class Ranking {
//    CREATE TABLE ranking_monthly (
//            rank_id INT PRIMARY KEY IDENTITY(1,1),
//    user_id INT,
//    rank_points INT,
//    month INT,
//    year INT,
//    FOREIGN KEY (user_id) REFERENCES users(user_id),
//    CONSTRAINT UQ_user_month_year UNIQUE (user_id, month, year)
//);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rank_id;
    private Integer rank_points;
    private Integer month;
    private Integer year;
   @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
