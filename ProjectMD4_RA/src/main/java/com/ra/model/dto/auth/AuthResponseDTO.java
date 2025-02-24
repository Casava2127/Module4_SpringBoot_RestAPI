package com.ra.model.dto.auth;

import lombok.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponseDTO {
    private Long userId;
    private String username;
    private String token;
    private Date issuedAt;
    private Date expiresAt;
}
