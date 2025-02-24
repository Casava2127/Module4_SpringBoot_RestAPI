package com.ra.service.auth.imp;

import com.ra.model.dto.auth.SignInRequestDTO;
import com.ra.model.dto.auth.SignUpRequestDTO;
import com.ra.model.dto.auth.AuthResponseDTO;
import com.ra.model.entity.*;
import com.ra.repository.RoleRepository;
import com.ra.repository.UserRepository;
import com.ra.repository.UserRoleRepository;
import com.ra.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ra.model.entity.RoleName;

import java.util.Optional;

@Service
public class AuthServiceImp implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    // @Autowired
    // private PasswordEncoder passwordEncoder; // Bỏ đi hoặc không sử dụng

    @Override
    public void signUp(SignUpRequestDTO signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new RuntimeException("Username already exists!");
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new RuntimeException("Email already exists!");
        }

        // Không mã hóa mật khẩu, lưu raw password (KHÔNG an toàn!)
        User user = User.builder()
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .fullname(signUpRequest.getFullname())
                .password(signUpRequest.getPassword()) // Nếu không dùng BCrypt, giữ nguyên mật khẩu
                .avatar(signUpRequest.getAvatar())
                .phone(signUpRequest.getPhone())
                .address(signUpRequest.getAddress())
                .isDeleted(false) // Mặc định là chưa bị xóa
                .build();

        User savedUser = userRepository.save(user);

        Optional<Role> defaultRoleOpt = roleRepository.findByRoleName(RoleName.STUDENT);


        if (!defaultRoleOpt.isPresent()) {
            throw new RuntimeException("Default role not found");
        }
        Role defaultRole = defaultRoleOpt.get();

        UserRole userRole = UserRole.builder()
                .id(new UserRoleId(savedUser.getUserId(), defaultRole.getRoleId()))
                .user(savedUser)
                .role(defaultRole)
                .build();
        userRoleRepository.save(userRole);
    }

    @Override
    public AuthResponseDTO signIn(SignInRequestDTO signInRequest) {
        User user = userRepository.findByUsername(signInRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // So sánh mật khẩu raw trực tiếp (KHÔNG an toàn!)
        if (!signInRequest.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = "fake-jwt-token-" + System.currentTimeMillis();

        return AuthResponseDTO.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .build();
    }
}


//{
//  "username": "newuser1231",
//  "email": "newus1231er@example.com",
//  "fullname": "New Use1231r",
//  "password": "passwo1123d123",
//  "phone": "0121234789",
//  "address": "122323 New Street",
//  "avatar": "https://example.com/ava12t12ar.jpg"
//}


//{
//    "accessToken": "fake-jwt-token-1740395288274",
//    "tokenType": "Bearer"
//}
