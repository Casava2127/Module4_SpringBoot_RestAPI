package com.ra.model.dto;

import com.ra.model.Role;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserRegisterResponseDTO {
    private String username;
    private Set<Role> roles;
}
