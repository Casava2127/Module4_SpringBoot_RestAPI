package com.ra.model.dto.auth;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponseDTO {
    private String accessToken;
    private String tokenType; // Ví dụ: "Bearer"
}
