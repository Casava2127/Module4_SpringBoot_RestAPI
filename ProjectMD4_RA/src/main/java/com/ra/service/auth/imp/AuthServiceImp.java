package com.ra.service.auth.imp;

import com.ra.model.dto.auth.SignUpRequestDTO;
import com.ra.model.dto.auth.SignInRequestDTO;
import com.ra.model.dto.auth.AuthResponseDTO;
import com.ra.model.entity.User;
import com.ra.repository.UserRepository;
import com.ra.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthServiceImp implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public AuthResponseDTO register(SignUpRequestDTO signUpDTO) {
        // Chuyển DTO thành Entity (trong thực tế cần mã hoá mật khẩu)
        User user = User.builder()
                .username(signUpDTO.getUsername())
                .email(signUpDTO.getEmail())
                .fullname(signUpDTO.getFullname())
                .password(signUpDTO.getPassword())
                .phone(signUpDTO.getPhone())
                .address(signUpDTO.getAddress())
                .avatar(signUpDTO.getAvatar())
                .build();
        User savedUser = userRepository.save(user);
        // Sinh token (ví dụ dùng JWT; ở đây chỉ demo với token mẫu)
        String token = generateToken(savedUser);
        return AuthResponseDTO.builder()
                .userId(savedUser.getUserId())
                .username(savedUser.getUsername())
                .token(token)
                .issuedAt(new Date())
                .expiresAt(new Date(System.currentTimeMillis() + 3600 * 1000)) // token hết hạn sau 1 giờ
                .build();
    }

    @Override
    public AuthResponseDTO authenticate(SignInRequestDTO signInDTO) {
        User user = userRepository.findByUsername(signInDTO.getUsername());
        // Ở đây so sánh mật khẩu dạng plaintext, nên cần thay thế bằng kiểm tra mã hoá trong thực tế
        if (user != null && user.getPassword().equals(signInDTO.getPassword())) {
            String token = generateToken(user);
            return AuthResponseDTO.builder()
                    .userId(user.getUserId())
                    .username(user.getUsername())
                    .token(token)
                    .issuedAt(new Date())
                    .expiresAt(new Date(System.currentTimeMillis() + 3600 * 1000))
                    .build();
        }
        return null;
    }

    // Phương thức tạo token mẫu
    private String generateToken(User user) {
        return "dummy-token-for-user-" + user.getUserId();
    }
}
