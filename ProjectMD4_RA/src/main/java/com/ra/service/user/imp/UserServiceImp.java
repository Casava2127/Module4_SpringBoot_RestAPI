package com.ra.service.user.imp;

import com.ra.model.dto.user.ChangePasswordDTO;
import com.ra.model.dto.user.UserRequestDTO;
import com.ra.model.dto.user.UserResponseDTO;
import com.ra.model.entity.User;
import com.ra.repository.UserRepository;
import com.ra.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserResponseDTO> findAll() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserResponseDTO> findById(Long id) {
        return userRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public UserResponseDTO save(UserRequestDTO userDTO) {
        User user = convertToEntity(userDTO);
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    @Override
    public UserResponseDTO update(Long id, UserRequestDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            return null;
        }

        User existingUser = optionalUser.get();
        existingUser.setUsername(userDTO.getUsername());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setFullname(userDTO.getFullname());
        existingUser.setStatus(userDTO.isStatus());
        existingUser.setPassword(userDTO.getPassword());
        existingUser.setPhone(userDTO.getPhone());
        existingUser.setAddress(userDTO.getAddress());
        existingUser.setAvatar(userDTO.getAvatar());

        User updatedUser = userRepository.save(existingUser);
        return convertToDTO(updatedUser);
    }

    @Override
    public boolean delete(Long id) {
        if (!userRepository.existsById(id)) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }
    @Override
    public boolean changePassword(Long userId, ChangePasswordDTO changePasswordDTO) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found!");
        }
        User user = optionalUser.get();
        // lay ra va so sanh , chi dinh id mac dinh la 13
        System.out.println("Stored password: " + user.getPassword());
        System.out.println("Provided old password: " + changePasswordDTO.getOldPassword());

        // So sánh mật khẩu raw trực tiếp
        if (!changePasswordDTO.getOldPassword().equals(user.getPassword())) {
            throw new RuntimeException("Old password is incorrect!");
        }

        // Kiểm tra mật khẩu mới và xác nhận mật khẩu
        if (!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmNewPassword())) {
            throw new RuntimeException("New password and confirmation do not match!");
        }



        // Cập nhật mật khẩu dưới dạng plain text (không mã hóa)
        user.setPassword(changePasswordDTO.getNewPassword());
        userRepository.save(user);
        return true;
    }

    // Chuyển đổi Entity ⇆ DTO
    private UserResponseDTO convertToDTO(User user) {
        return UserResponseDTO.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullname(user.getFullname())
                .status(user.isStatus())
                .phone(user.getPhone())
                .address(user.getAddress())
                .avatar(user.getAvatar())
                .build();
    }

    private User convertToEntity(UserRequestDTO userDTO) {
        return User.builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .fullname(userDTO.getFullname())
                .status(userDTO.isStatus())
                .password(userDTO.getPassword())
                .phone(userDTO.getPhone())
                .address(userDTO.getAddress())
                .avatar(userDTO.getAvatar())
                .build();
    }
}
