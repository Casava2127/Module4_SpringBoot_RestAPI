package com.ra.controller;

import com.ra.model.dto.auth.SignInRequestDTO;
import com.ra.model.dto.auth.SignUpRequestDTO;
import com.ra.model.dto.auth.AuthResponseDTO;
import com.ra.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api.myservice.com/v1/auth")
@Validated
public class AuthController {

    @Autowired
    private AuthService authService;

    // Đăng ký tài khoản mới
    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponseDTO> signUp(@RequestBody @Validated SignUpRequestDTO signUpDTO) {
        AuthResponseDTO response = authService.register(signUpDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Đăng nhập
    @PostMapping("/sign-in")
    public ResponseEntity<AuthResponseDTO> signIn(@RequestBody @Validated SignInRequestDTO signInDTO) {
        AuthResponseDTO response = authService.authenticate(signInDTO);
        return (response != null)
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
