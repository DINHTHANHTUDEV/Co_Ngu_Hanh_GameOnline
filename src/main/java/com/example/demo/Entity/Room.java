package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "rooms")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    Integer roomID;
    @Column(name = "room_code")
    String roomCode;
    @Column(name = "elo_points")
    Integer elo_points;
    @Column(name = "is_private")
    Boolean is_private;
    @Column(name = "password")
    String password;
    @Column(name = "created_at")
    LocalDateTime created_at;
    @Column(name = "status")
    String status;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @PrePersist
    public void prePersist() {
        created_at = LocalDateTime.now();
        status = "WAITING";
    }
}
