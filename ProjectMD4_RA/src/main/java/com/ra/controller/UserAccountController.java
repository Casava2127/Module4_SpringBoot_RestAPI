//package com.ra.controller;
//
//import com.ra.model.dto.user.UserAccountDTO;
//import com.ra.model.dto.user.UserRequestDTO;
//import com.ra.model.dto.user.ChangePasswordDTO;
//import com.ra.service.user.UserAccountService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import java.security.Principal;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/v1/user/account")
//@Validated
//public class UserAccountController {
//
//    @Autowired
//    private UserAccountService userAccountService;
//
//    // Lấy thông tin tài khoản
//    @GetMapping
//    public ResponseEntity<UserAccountDTO> getUserAccount(Principal principal) {
//        Long userId = getUserIdFromPrincipal(principal);
//        Optional<UserAccountDTO> userAccount = userAccountService.getUserAccount(userId);
//        return userAccount.map(ResponseEntity::ok)
//                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
//
//    // Cập nhật thông tin tài khoản
//    @PutMapping
//    public ResponseEntity<UserAccountDTO> updateUserAccount(
//            Principal principal,
//            @RequestBody @Validated UserRequestDTO userDTO) {
//        Long userId = getUserIdFromPrincipal(principal);
//        UserAccountDTO updatedUser = userAccountService.updateUserAccount(userId, userDTO);
//        return ResponseEntity.ok(updatedUser);
//    }
//
//    // Đổi mật khẩu
//    @PutMapping("/change-password")
//    public ResponseEntity<String> changePassword(
//            Principal principal,
//            @RequestBody @Validated ChangePasswordDTO changePasswordDTO) {
//        Long userId = getUserIdFromPrincipal(principal);
//        boolean success = userAccountService.changePassword(userId, changePasswordDTO);
//        return success ? ResponseEntity.ok("Password updated successfully!")
//                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password change failed!");
//    }
//
//    private Long getUserIdFromPrincipal(Principal principal) {
//        // Giả sử `principal.getName()` là username, cần tìm userId từ username.
//        // Ở đây cần có UserService hoặc UserRepository để lấy userId.
//        // Để đơn giản, giả sử userId = 1 (cần thay thế bằng cách truy vấn thực tế).
//        return 1L;
//    }
//}
