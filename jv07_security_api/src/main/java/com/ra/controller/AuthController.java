package com.ra.controller;

import com.ra.model.User;
import com.ra.model.dto.UserLoginRequestDTO;
import com.ra.model.dto.UserLoginResponse;
import com.ra.model.dto.UserRegisterDTO;
import com.ra.model.dto.UserRegisterResponseDTO;
import com.ra.service.AuthService;
import com.ra.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private TokenService tokenService;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserLoginRequestDTO requestDTO){
        UserLoginResponse userLoginResponse = authService.login(requestDTO);
        return new ResponseEntity<>(userLoginResponse, HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDTO userRegisterDTO){
        UserRegisterResponseDTO userRegisterResponseDTO = authService.register(userRegisterDTO);
        return new ResponseEntity<>(userRegisterResponseDTO,HttpStatus.OK);
    }
    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if(token != null & token.startsWith("Bearer ")){
            token = token.substring(7);
            tokenService.invalidateToken(token);
        }
        return ResponseEntity.ok("Đăng xuất");
    }
}
