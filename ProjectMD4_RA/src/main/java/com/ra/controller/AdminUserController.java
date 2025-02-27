package com.ra.controller;

import com.ra.model.dto.course.CourseResponseDTO;
import com.ra.model.dto.user.UserResponseDTO;
import com.ra.model.dto.user.UserStatusUpdateDTO;
import com.ra.service.course.CourseService;
import com.ra.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin/users")
public class AdminUserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<Page<UserResponseDTO>> getAllAdminUsers(Pageable pageable) {
        Page<UserResponseDTO> users = userService.findAll((java.awt.print.Pageable) pageable);
        return ResponseEntity.ok(users);
    }



    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long userId) {
        Optional<UserResponseDTO> user = userService.findById(userId);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{userId}/status")
    public ResponseEntity<String> updateUserStatus(@PathVariable Long userId, @RequestBody UserStatusUpdateDTO userStatusUpdateDTO) {
        boolean updated = userService.updateUserStatus(userId, userStatusUpdateDTO);
        if (updated) {
            return ResponseEntity.ok("User account status updated successfully.");
        }
        return ResponseEntity.notFound().build();
    }



}
