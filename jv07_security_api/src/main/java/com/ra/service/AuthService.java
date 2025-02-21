package com.ra.service;

import com.ra.model.dto.*;

public interface AuthService {
    UserLoginResponse login(UserLoginRequestDTO userLoginRequestDTO);
    UserRegisterResponseDTO register(UserRegisterDTO userRegisterDTO);
    UserRegisterResponseDTO updatePermission(UserPermissionDTO userPermissionDTO,Long userId) throws Exception;
}
