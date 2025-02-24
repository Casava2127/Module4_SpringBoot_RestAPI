package com.ra.model.dto.auth;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignInRequestDTO {
    private String username;
    private String password;
}
