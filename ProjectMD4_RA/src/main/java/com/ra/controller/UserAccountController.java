package com.ra.controller;

import com.ra.model.dto.user.UserRequestDTO;
import com.ra.model.dto.user.UserResponseDTO;
import com.ra.model.dto.user.ChangePasswordDTO;
import com.ra.service.user.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api.myservice.com/v1/user/account")
@Validated
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;

    // Lấy thông tin tài khoản người dùng
    @GetMapping
    public ResponseEntity<UserResponseDTO> getAccount(@RequestParam Long userId) {
        UserResponseDTO account = userAccountService.getAccount(userId);
        return (account != null)
                ? new ResponseEntity<>(account, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Cập nhật thông tin người dùng
    @PutMapping
    public ResponseEntity<UserResponseDTO> updateAccount(@RequestBody @Validated UserRequestDTO userDTO) {
        UserResponseDTO updatedAccount = userAccountService.updateAccount(userDTO);
        return (updatedAccount != null)
                ? new ResponseEntity<>(updatedAccount, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Đổi mật khẩu người dùng
    @PutMapping("/change-password")
    public ResponseEntity<Void> changePassword(@RequestBody @Validated ChangePasswordDTO changePasswordDTO) {
        boolean success = userAccountService.changePassword(changePasswordDTO);
        return success ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
