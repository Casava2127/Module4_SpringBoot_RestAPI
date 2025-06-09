package com.ac.service;

import com.ac.model.dto.*;
import com.ac.model.entity.RoleType;
import com.ac.model.entity.User;
import com.ac.repository.UserRepository;
import com.ac.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;


    // Đăng nhập: Kiểm tra email và so sánh mật khẩu (plain text)
    public LoginResponse login(LoginRequest request) {
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // So sánh mật khẩu hiện tại (plain text)
            if (user.getPassword().equals(request.getPassword())) {
                // Tạo token dựa trên email hoặc id
                String token = jwtUtil.generateToken(user.getEmail());
                return new LoginResponse(
                        user.getEmail(),
                        token,
                        user.getFullName(),
                        user.getRole().name());
            }
        }
        return null;  // Hoặc có thể ném exception
    }


    // Đăng ký: Kiểm tra email trùng và lưu mới (mật khẩu không được mã hóa)
    public RegisterResponse register(RegistrationRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email đã được sử dụng");
        }
        // Kiểm tra role nếu có
        RoleType role = RoleType.SINH_VIEN; // mặc định
        if (request.getRole() != null && !request.getRole().isBlank()) {
            try {
                role = RoleType.valueOf(request.getRole().toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Vai trò không hợp lệ: " + request.getRole());
            }
        }
        User user = User.builder()
                .email(request.getEmail())
                .fullName(request.getFullName())
                .phoneNumber(request.getPhoneNumber())
                .password(request.getPassword()) // ⚠️ nhớ hash ở bước thực tế
                .role(role)
                .build();
        userRepository.save(user);
        String token = jwtUtil.generateToken(user.getEmail());

        return new RegisterResponse(token, user.getUserId(), user.getFullName(), user.getEmail(), user.getRole().name(), user.getCreatedAt());
    }

    // Thay đổi mật khẩu: Xác thực mật khẩu cũ rồi cập nhật mới (plain text)
    public void changePassword(ChangePasswordRequest request) {
        Optional<User> userOpt = userRepository.findById(request.getUserId());
        if (userOpt.isEmpty()) {
            throw new RuntimeException("Người dùng không tồn tại");
        }
        User user = userOpt.get();
        if (!user.getPassword().equals(request.getOldPassword())) {
            throw new RuntimeException("Mật khẩu cũ không đúng");
        }
        user.setPassword(request.getNewPassword());
        userRepository.save(user);
    }
}
