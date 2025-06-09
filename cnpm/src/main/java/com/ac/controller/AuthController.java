package com.ac.controller;

import com.ac.model.dto.*;
import com.ac.model.entity.User;
import com.ac.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        LoginResponse loginResponse = authService.login(request);
        if (loginResponse == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>("error", "Thông tin đăng nhập không hợp lệ", null));
        }
        ApiResponse<LoginResponse> response = new ApiResponse<>("success", "Đăng nhập thành công", loginResponse);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest request) {
        try {
            RegisterResponse response = authService.register(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("success", "Đăng ký thành cong", response));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of(
                            "status", "error",
                            "message", ex.getMessage()
                    ));
        }
    }
//http://localhost:8080/api/v1/auth/register
//    {
//  "email": "testuser@gmail.com",
//  "password": "123456",
//  "fullName": "Nguyễn Văn Test",
//  "phoneNumber": "0987654321",
//  "role": "SINH_VIEN"
//}

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request) {
        try {
            authService.changePassword(request);
            return ResponseEntity.ok("Đổi mật khẩu thành công");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}