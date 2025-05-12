package com.example.matching_history.Entity;

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
@Table(name = "users")
public class User {
//    CREATE TABLE users (
//            user_id INT IDENTITY(1,1) PRIMARY KEY,
//    username NVARCHAR(100) UNIQUE,
//    password NVARCHAR(100),
//    email NVARCHAR(100) UNIQUE,
//    phone_number NVARCHAR(20),
//    is_online BIT DEFAULT 0,
//    last_online DATETIME NULL,
//    last_offline DATETIME NULL
//);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;
    private String username;
}
