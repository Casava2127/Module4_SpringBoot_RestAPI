package com.ra.model.dto.userrole;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserRoleDTO {
    private Long userId;
    private Long roleId;
}
