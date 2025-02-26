package com.ra.controller;

import com.ra.model.dto.CourseCartRequestDTO;
import com.ra.model.dto.CourseCartResponseDTO;
import com.ra.model.dto.payment.PaymentRequestDTO;
import com.ra.model.dto.payment.PaymentResponseDTO;
import com.ra.service.course.CourseCartService;
import com.ra.service.payment.PaymentService;
import com.ra.service.payment.imp.PaymentServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/cart")
@RequiredArgsConstructor
public class CourseCartController {

    private final CourseCartService courseCartService;
    private final PaymentService paymentService;

    // ✅ Lấy danh sách khóa học trong giỏ hàng
    @GetMapping("/{userId}")
    public ResponseEntity<List<CourseCartResponseDTO>> getCartItems(@PathVariable Long userId) {
        if (userId == null) {
            return ResponseEntity.badRequest().body(null);
        }
        List<CourseCartResponseDTO> cartItems = courseCartService.getCartItems(userId);
        return ResponseEntity.ok(cartItems);
    }

    // ✅ Thêm khóa học vào giỏ hàng
    @PostMapping
    public ResponseEntity<String> addToCart(@RequestParam(required = false) Long userId,
                                            @RequestBody CourseCartRequestDTO requestDTO) {
        if (userId == null) {
            return ResponseEntity.badRequest().body("User ID must not be null");
        }
        if (requestDTO == null || requestDTO.getCourseId() == null) {
            return ResponseEntity.badRequest().body("Invalid course data");
        }
        courseCartService.addToCart(userId, requestDTO);
        return ResponseEntity.ok("Course added to cart successfully");
    }

    // ✅ Cập nhật số lượng khóa học trong giỏ hàng
    @PutMapping("/{cartId}")
    public ResponseEntity<String> updateCartItem(@RequestParam(required = false) Long userId,
                                                 @PathVariable Long cartId,
                                                 @RequestParam int quantity) {
        if (userId == null) {
            return ResponseEntity.badRequest().body("User ID must not be null");
        }
        if (cartId == null) {
            return ResponseEntity.badRequest().body("Cart ID must not be null");
        }
        courseCartService.updateCartItem(userId, cartId, quantity);
        return ResponseEntity.ok("Cart item updated successfully");
    }

    // ✅ Xóa một mục trong giỏ hàng
    @DeleteMapping("/{cartId}")
    public ResponseEntity<String> removeCartItem(@RequestParam(required = false) Long userId,
                                                 @PathVariable Long cartId) {
        if (userId == null) {
            return ResponseEntity.badRequest().body("User ID must not be null");
        }
        if (cartId == null) {
            return ResponseEntity.badRequest().body("Cart ID must not be null");
        }
        courseCartService.removeCartItem(userId, cartId);
        return ResponseEntity.ok("Cart item removed successfully");
    }

    // ✅ Xóa toàn bộ giỏ hàng
    @DeleteMapping("/clear")
    public ResponseEntity<String> clearCart(@RequestParam(required = false) Long userId) {
        if (userId == null) {
            return ResponseEntity.badRequest().body("User ID must not be null");
        }
        courseCartService.clearCart(userId);
        return ResponseEntity.ok("Cart cleared successfully");
    }

    @PostMapping("/checkout")
    public ResponseEntity<PaymentResponseDTO> checkoutCart(@RequestBody PaymentRequestDTO paymentRequestDTO) {
        PaymentResponseDTO response = paymentService.checkoutCart(paymentRequestDTO);
        return ResponseEntity.ok(response);
    }
}
