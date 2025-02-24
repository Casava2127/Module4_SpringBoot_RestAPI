//package com.ra.service.user.imp;
//
//import com.ra.model.dto.user.UserAccountDTO;
//import com.ra.model.dto.user.UserRequestDTO;
//import com.ra.model.dto.user.ChangePasswordDTO;
//import com.ra.model.entity.User;
//import com.ra.repository.UserAccountRepository;
//import com.ra.repository.UserRepository;
//import com.ra.service.user.UserAccountService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class UserAccountServiceImp implements UserAccountService {
//
//    @Autowired
//    private UserAccountRepository userRepository;
//
//    @Override
//    public Optional<UserAccountDTO> getUserAccount(Long userId) {
//        return userRepository.findById(userId).map(this::convertToDTO);
//    }
//
//    @Override
//    public UserAccountDTO updateUserAccount(Long userId, UserRequestDTO userDTO) {
//        Optional<User> optionalUser = userRepository.findById(userId);
//        if (optionalUser.isEmpty()) {
//            throw new RuntimeException("User not found!");
//        }
//        User user = optionalUser.get();
//        user.setFullname(userDTO.getFullname());
//        user.setPhone(userDTO.getPhone());
//        user.setAddress(userDTO.getAddress());
//        user.setAvatar(userDTO.getAvatar());
//
//        User updatedUser = userRepository.save(user);
//        return convertToDTO(updatedUser);
//    }
//
//    @Override
//    public boolean changePassword(Long userId, ChangePasswordDTO changePasswordDTO) {
//        Optional<User> optionalUser = userRepository.findById(userId);
//        if (optionalUser.isEmpty()) {
//            throw new RuntimeException("User not found!");
//        }
//        User user = optionalUser.get();
//
//        // So sánh mật khẩu raw trực tiếp
//        if (!changePasswordDTO.getOldPassword().equals(user.getPassword())) {
//            throw new RuntimeException("Old password is incorrect!");
//        }
//
//        // Kiểm tra mật khẩu mới và xác nhận mật khẩu
//        if (!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmNewPassword())) {
//            throw new RuntimeException("New password and confirmation do not match!");
//        }
//
//        // Cập nhật mật khẩu dưới dạng plain text (không mã hóa)
//        user.setPassword(changePasswordDTO.getNewPassword());
//        userRepository.save(user);
//        return true;
//    }
//
//    private UserAccountDTO convertToDTO(User user) {
//        return UserAccountDTO.builder()
//                .userId(user.getUserId())
//                .username(user.getUsername())
//                .email(user.getEmail())
//                .fullname(user.getFullname())
//                .phone(user.getPhone())
//                .address(user.getAddress())
//                .avatar(user.getAvatar())
//                .build();
//    }
//}
