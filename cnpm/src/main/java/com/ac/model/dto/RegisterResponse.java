package com.ac.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponse {

    private String token;
    private Long id;
    private String name;
    private String email;
    private String role;
    private LocalDateTime createdAt;

}
