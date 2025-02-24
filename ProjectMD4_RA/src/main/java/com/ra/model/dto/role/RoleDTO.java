package com.ra.model.dto.role;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RoleDTO {
    private Long roleId;
    private String roleName;
}
