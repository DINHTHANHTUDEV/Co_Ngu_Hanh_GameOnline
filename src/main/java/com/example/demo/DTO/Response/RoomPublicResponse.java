package com.example.demo.DTO.Response;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RoomPublicResponse {
    private Integer roomId;
    private String roomCode;
    private Integer eloPoints;
    private String hostUsername;
    private LocalDateTime createdAt;
}
