package com.ra.controller;

import com.ra.model.dto.UserPermissionDTO;
import com.ra.model.dto.UserRegisterResponseDTO;
import com.ra.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/")
public class AdminController {
    @Autowired
    private AuthService authService;
    @GetMapping("accounts")
    public ResponseEntity<?> account(){
        return new ResponseEntity<>("Trang quan ly tai khoan", HttpStatus.OK);
    }
    @GetMapping("blogs")
    public ResponseEntity<?> blog(){
        return new ResponseEntity<>("Trang quan ly baif viet", HttpStatus.OK);
    }
    @PatchMapping("accounts/{id}")
    public ResponseEntity<?> updatePermissionUser(@RequestBody @Valid UserPermissionDTO userPermissionDTO, @PathVariable String id) throws Exception {
        UserRegisterResponseDTO userRegisterResponseDTO = authService.updatePermission(userPermissionDTO, Long.valueOf(id));
        return new ResponseEntity<>(userRegisterResponseDTO,HttpStatus.OK);
    }
}
