package com.example.user.DTO;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class UserDTO {
    private Integer user_id;
    private String username;

    @NotNull(message = "Trạng thái online không được để trống")
    @Min(value = 0, message = "Trạng thái online phải là 0 hoặc 1")
    @Max(value = 1, message = "Trạng thái online phải là 0 hoặc 1")
    private Integer is_online;
    private LocalDateTime last_online;
    private LocalDateTime last_offline;
}
