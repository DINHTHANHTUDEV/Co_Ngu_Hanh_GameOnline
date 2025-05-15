package com.example.matching.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MatchMakingDTO {
    @NotNull(message = "Mã người dùng là bắt buộc")
    private Integer userId;
    private String username; // Sửa từ userName thành username
    private Integer queue_id;
    private LocalDateTime entered_at;
}
