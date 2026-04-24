package org.smartkup.smartkup.dto;

import lombok.Data;
import org.smartkup.smartkup.entity.User;
import java.time.LocalDateTime;

@Data
public class UserResponseDTO {
    private Long userId;
    private String username;
    private String email;
    private LocalDateTime createdAt;

    public UserResponseDTO(User user) {
        this.userId = user.getUser_id();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
    }
}