package com.ra.service.user.imp;

import com.ra.model.dto.user.UserRequestDTO;
import com.ra.model.dto.user.UserResponseDTO;
import com.ra.model.dto.user.ChangePasswordDTO;
import com.ra.model.entity.User;
import com.ra.repository.UserRepository;
import com.ra.service.user.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAccountServiceImp implements UserAccountService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponseDTO getAccount(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.map(this::convertToDTO).orElse(null);
    }

    @Override
    public UserResponseDTO updateAccount(UserRequestDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(userDTO.getUserId());
        if (!optionalUser.isPresent()) {
            return null;
        }
        User user = optionalUser.get();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setFullname(userDTO.getFullname());
        user.setPhone(userDTO.getPhone());
        user.setAddress(userDTO.getAddress());
        user.setAvatar(userDTO.getAvatar());
        // Có thể cập nhật mật khẩu nếu được yêu cầu (nhớ mã hoá mật khẩu)
        if(userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()){
            user.setPassword(userDTO.getPassword());
        }
        User updatedUser = userRepository.save(user);
        return convertToDTO(updatedUser);
    }

    @Override
    public boolean changePassword(ChangePasswordDTO changePasswordDTO) {
        Optional<User> optionalUser = userRepository.findById(changePasswordDTO.getUserId());
        if (!optionalUser.isPresent()) {
            return false;
        }
        User user = optionalUser.get();
        // Kiểm tra mật khẩu cũ (plaintext, cần mã hoá trong thực tế)
        if (!user.getPassword().equals(changePasswordDTO.getOldPassword())) {
            return false;
        }
        // Kiểm tra confirm new password
        if (!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmNewPassword())) {
            return false;
        }
        user.setPassword(changePasswordDTO.getNewPassword());
        userRepository.save(user);
        return true;
    }

    private UserResponseDTO convertToDTO(User user) {
        return UserResponseDTO.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullname(user.getFullname())
                .phone(user.getPhone())
                .address(user.getAddress())
                .avatar(user.getAvatar())
                .status(user.isStatus())
                .build();
    }
}
