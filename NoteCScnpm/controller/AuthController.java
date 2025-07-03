package com.ac.Note.controller;

import com.ac.model.dto.ChangePasswordRequest;
import com.ac.model.dto.LoginRequest;
import com.ac.model.dto.RegistrationRequest;
import com.ac.model.entity.User;
import com.ac.Note.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // Đăng nhập
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = authService.login(request);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Thông tin đăng nhập không hợp lệ");
        }
        // Ở đây có thể trả về JWT token thay vì thông tin user
        return ResponseEntity.ok(user);
    }

    // Đăng ký
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest request) {
        try {
            User user = authService.register(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    // Thay đổi mật khẩu
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
//Controller này có các chức năng chính sau:
//
//1. **Đăng nhập (Login)**
//   - **Endpoint:** `POST /api/v1/auth/login`
//   - **Chức năng:** Xác thực thông tin đăng nhập của người dùng bằng cách nhận dữ liệu từ `LoginRequest` (bao gồm email và password).
//   - **Quá trình:**
//     - Gọi service để kiểm tra thông tin đăng nhập.
//     - Nếu thông tin hợp lệ, trả về đối tượng `User` (hoặc JWT token nếu tích hợp token).
//     - Nếu không hợp lệ, trả về mã lỗi 401 cùng thông báo "Thông tin đăng nhập không hợp lệ".
//
//2. **Đăng ký (Registration)**
//   - **Endpoint:** `POST /api/v1/auth/register`
//   - **Chức năng:** Tạo tài khoản người dùng mới bằng cách nhận dữ liệu từ `RegistrationRequest` (email, password, fullName, phoneNumber).
//   - **Quá trình:**
//     - Kiểm tra xem email đã tồn tại chưa.
//     - Nếu chưa, tạo mới đối tượng `User` và lưu vào database.
//     - Nếu email đã được sử dụng, trả về mã lỗi 400 với thông báo lỗi tương ứng.
//
//3. **Thay đổi mật khẩu (Change Password)**
//   - **Endpoint:** `POST /api/v1/auth/change-password`
//   - **Chức năng:** Cập nhật mật khẩu cho người dùng sau khi xác thực mật khẩu cũ bằng cách nhận dữ liệu từ `ChangePasswordRequest` (bao gồm userId, oldPassword, newPassword).
//   - **Quá trình:**
//     - Xác thực mật khẩu cũ của người dùng.
//     - Nếu đúng, cập nhật mật khẩu mới và lưu vào database.
//     - Nếu sai, trả về mã lỗi 400 với thông báo "Mật khẩu cũ không đúng" hoặc thông báo lỗi tương ứng.
//
//Như vậy, controller này cung cấp các API cơ bản cho việc quản lý xác thực người dùng trong hệ thống.