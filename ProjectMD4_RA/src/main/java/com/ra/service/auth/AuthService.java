package com.ra.service.auth;

import com.ra.model.dto.auth.SignUpRequestDTO;
import com.ra.model.dto.auth.SignInRequestDTO;
import com.ra.model.dto.auth.AuthResponseDTO;

public interface AuthService {
    AuthResponseDTO register(SignUpRequestDTO signUpDTO);
    AuthResponseDTO authenticate(SignInRequestDTO signInDTO);
}
