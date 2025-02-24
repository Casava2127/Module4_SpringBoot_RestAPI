package com.ra.model.dto.auth;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpRequestDTO {
    private String username;
    private String email;
    private String fullname;
    private String password;
    private String phone;
    private String address;
    private String avatar;
}
