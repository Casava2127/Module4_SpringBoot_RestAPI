package com.ac.Note.service;

import com.ac.model.dto.ChangePasswordRequest;
import com.ac.model.dto.LoginRequest;
import com.ac.model.dto.RegistrationRequest;
import com.ac.model.entity.RoleType;
import com.ac.model.entity.User;
import com.ac.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    // Đăng nhập: Kiểm tra email và so sánh mật khẩu (plain text)
    public User login(LoginRequest request) {
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getPassword().equals(request.getPassword())) {
                return user; // Có thể trả về token nếu tích hợp JWT
            }
        }
        return null; // Hoặc ném exception tùy theo cách xử lý
    }

    // Đăng ký: Kiểm tra email trùng và lưu mới (mật khẩu không được mã hóa)
    public User register(RegistrationRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email đã được sử dụng");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(request.getPassword());
        // Đặt role mặc định là SINH_VIEN (có thể điều chỉnh theo yêu cầu)
        user.setRole(RoleType.valueOf("SINH_VIEN"));
        return userRepository.save(user);
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
