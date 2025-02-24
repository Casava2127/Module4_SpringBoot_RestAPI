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
@RequestMapping("/api/v1/auth")
@Validated
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody @Validated SignUpRequestDTO signUpRequest) {
        authService.signUp(signUpRequest);
        return new ResponseEntity<>("User registered successfully!", HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<AuthResponseDTO> signIn(@RequestBody @Validated SignInRequestDTO signInRequest) {
        AuthResponseDTO response = authService.signIn(signInRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
