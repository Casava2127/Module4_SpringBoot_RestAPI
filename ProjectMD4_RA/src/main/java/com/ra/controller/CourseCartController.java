package com.ra.controller;

import com.ra.model.dto.CourseCartRequestDTO;
import com.ra.model.dto.CourseCartResponseDTO;
import com.ra.service.course.CourseCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/cart")
@RequiredArgsConstructor
public class CourseCartController {

    private final CourseCartService courseCartService;

    // Lấy danh sách khóa học trong giỏ
    @GetMapping("/{userId}")
    public ResponseEntity<List<CourseCartResponseDTO>> getCartItems(@PathVariable Long userId) {
        List<CourseCartResponseDTO> cartItems = courseCartService.getCartItems(userId);
        return ResponseEntity.ok(cartItems);
    }


    // Thêm khóa học vào giỏ
    @PostMapping
    public ResponseEntity<String> addToCart(@RequestParam Long userId, @RequestBody CourseCartRequestDTO requestDTO) {
        courseCartService.addToCart(userId, requestDTO);
        return ResponseEntity.ok("Course added to cart successfully");
    }

    // Cập nhật số lượng khóa học trong giỏ
    @PutMapping("/{cartId}")
    public ResponseEntity<String> updateCartItem(@RequestParam Long userId, @PathVariable Long cartId, @RequestParam int quantity) {
        courseCartService.updateCartItem(userId, cartId, quantity);
        return ResponseEntity.ok("Cart item updated successfully");
    }

    // Xóa một mục trong giỏ
    @DeleteMapping("/{cartId}")
    public ResponseEntity<String> removeCartItem(@RequestParam Long userId, @PathVariable Long cartId) {
        courseCartService.removeCartItem(userId, cartId);
        return ResponseEntity.ok("Cart item removed successfully");
    }

    // Xóa toàn bộ giỏ hàng
    @DeleteMapping("/clear")
    public ResponseEntity<String> clearCart(@RequestParam Long userId) {
        courseCartService.clearCart(userId);
        return ResponseEntity.ok("Cart cleared successfully");
    }
}
