//package com.ac.controller;//package com.ac.controller;
//
//
//import com.ac.model.entity.User;
//import com.ac.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/users")
//@RequiredArgsConstructor
//public class UserController {
//    private final UserService userService;
//
//    @GetMapping
//    public ResponseEntity<List<User>> getAllUsers() {
//        return ResponseEntity.ok(userService.getAllUsers());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable Long id) {
//        return userService.getUserById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @PostMapping
//    public ResponseEntity<User> createUser(@RequestBody User user) {
//        return ResponseEntity.ok(userService.createUser(user));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
//        return ResponseEntity.ok(userService.updateUser(id, user));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//        userService.deleteUser(id);
//        return ResponseEntity.noContent().build();
//    }
//}
//
////import com.ac.model.entity.User;
////import com.ac.service.UserService;
////import org.springframework.http.ResponseEntity;
////import org.springframework.security.access.prepost.PreAuthorize;
////import org.springframework.web.bind.annotation.*;
////
////import java.util.List;
////
////@RestController
////@RequestMapping("/api/v1/admin/users")
////public class UserController {
////    private final UserService userService;
////
////    public UserController(UserService userService) {
////        this.userService = userService;
////    }
////
////    // Chỉ ADMIN mới có thể truy cập
////    @PreAuthorize("hasRole('ADMIN')")
////    @GetMapping
////    public ResponseEntity<List<User>> getAllUsers() {
////        return ResponseEntity.ok(userService.getAllUsers());
////
////    }
////
////    // Chỉ ADMIN mới có thể truy cập
////    @PreAuthorize("hasRole('ADMIN')")
////    @PostMapping
////    public ResponseEntity<User> createUser(@RequestBody User user) {
////        return ResponseEntity.ok(userService.createUser(user));
////    }
////
////    @PreAuthorize("hasRole('ADMIN')")
////    @PutMapping("/{id}")
////    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
////        return ResponseEntity.ok(userService.updateUser(id, user));
////    }
////    @PreAuthorize("hasRole('ADMIN')")
////    @DeleteMapping("/{id}")
////    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
////        userService.deleteUser(id);
////        return ResponseEntity.noContent().build();
////    }
//
//
////}
//
