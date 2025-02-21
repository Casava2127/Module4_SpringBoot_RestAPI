package com.ra.model.dto;

import com.ra.model.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserPermissionDTO {
    @NotNull
    private Set<String> roleName;
}
