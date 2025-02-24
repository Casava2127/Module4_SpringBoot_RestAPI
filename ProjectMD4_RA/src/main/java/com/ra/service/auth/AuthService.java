package com.ra.service.auth;

import com.ra.model.dto.auth.SignInRequestDTO;
import com.ra.model.dto.auth.SignUpRequestDTO;
import com.ra.model.dto.auth.AuthResponseDTO;

public interface AuthService {
    void signUp(SignUpRequestDTO signUpRequest);
    AuthResponseDTO signIn(SignInRequestDTO signInRequest);
}
