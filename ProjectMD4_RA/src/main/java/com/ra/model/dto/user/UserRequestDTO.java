package com.ra.model.dto.user;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserRequestDTO {
    private Long userId; // Thêm thuộc tính này nếu cần dùng cho cập nhật

    @NotNull
    private String username;

    @NotNull
    private String email;

    @NotNull
    private String fullname;

    private boolean status = true; // Mặc định là Active

    @NotNull
    private String password;

    private String avatar;

    private String phone;

    @NotNull
    private String address;
}
