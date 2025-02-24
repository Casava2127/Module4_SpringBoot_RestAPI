package com.ra.service.user;

import com.ra.model.dto.user.UserRequestDTO;
import com.ra.model.dto.user.UserResponseDTO;
import com.ra.model.dto.user.ChangePasswordDTO;

public interface UserAccountService {
    UserResponseDTO getAccount(Long userId);
    UserResponseDTO updateAccount(UserRequestDTO userDTO);
    boolean changePassword(ChangePasswordDTO changePasswordDTO);
}
