package com.ra.model.dto.user;

import lombok.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserResponseDTO {
    private Long userId;
    private String username;
    private String email;
    private String fullname;
    private boolean status;
    private String avatar;
    private String phone;
    private String address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserResponseDTO(long l, String johnUpdated, String mail, String active) {
    }
}
