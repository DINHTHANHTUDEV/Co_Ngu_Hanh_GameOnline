package com.example.user.Entity;

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
@Table(name = "users")
public class User {
//    CREATE TABLE IF NOT EXISTS users (
//            user_id INT AUTO_INCREMENT PRIMARY KEY,
//            username VARCHAR(100) UNIQUE,
//    password VARCHAR(100),
//    email VARCHAR(100) UNIQUE,
//    phone_number VARCHAR(20),
//    is_online TINYINT(1) DEFAULT 0,
//    last_online DATETIME NULL,
//    last_offline DATETIME NULL
//)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;
    private String username;
    private String password;
    private String email;
    private String phone_number;
    private Integer is_online;
    private LocalDateTime last_online;
    private LocalDateTime last_offline;
}
