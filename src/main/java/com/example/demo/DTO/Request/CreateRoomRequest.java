package com.example.demo.DTO.Request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CreateRoomRequest {
    private Integer createdByUserId;
    private Integer eloPoints;
    private Boolean isPrivate;
    private String password;
}
