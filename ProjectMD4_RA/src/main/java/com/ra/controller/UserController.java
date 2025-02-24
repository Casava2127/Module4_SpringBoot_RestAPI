package com.ra.controller;

import com.ra.model.dto.user.ChangePasswordDTO;
import com.ra.model.dto.user.UserRequestDTO;
import com.ra.model.dto.user.UserResponseDTO;
import com.ra.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    // Lấy danh sách tất cả người dùng
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        List<UserResponseDTO> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Lấy thông tin người dùng theo ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable Long id) {
        Optional<UserResponseDTO> user = userService.findById(id);
        return user.map(responseDTO -> new ResponseEntity<>(responseDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Tạo mới người dùng
    @PostMapping("/create")
    public ResponseEntity<UserResponseDTO> create(@RequestBody @Validated UserRequestDTO userDTO) {
        UserResponseDTO newUser = userService.save(userDTO);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    // Cập nhật thông tin người dùng
    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @RequestBody @Validated UserRequestDTO userDTO) {
        UserResponseDTO updatedUser = userService.update(id, userDTO);
        if (updatedUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // Xóa người dùng
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = userService.delete(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(
            Principal principal,
            @RequestBody @Validated ChangePasswordDTO changePasswordDTO) {
        Long userId = getUserIdFromPrincipal(principal);
        boolean success = userService.changePassword(userId, changePasswordDTO);
        return success ? ResponseEntity.ok("Password updated successfully!")
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password change failed!");
    }

    private Long getUserIdFromPrincipal(Principal principal) {
        // Giả sử `principal.getName()` là username, cần tìm userId từ username.
        // Ở đây cần có UserService hoặc UserRepository để lấy userId.
        // Để đơn giản, giả sử userId = 1 (cần thay thế bằng cách truy vấn thực tế).
        return 13L;  // câần sửa lại - đay chỉ là mawcjh định
    }
}
