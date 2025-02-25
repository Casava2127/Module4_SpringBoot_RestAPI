package com.ra.controller;

import com.ra.model.dto.enrollment.EnrollmentResponseDTO;
import com.ra.model.dto.payment.PaymentRequestDTO;
import com.ra.model.dto.payment.PaymentResponseDTO;
import com.ra.service.enrollment.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    // API checkout cart
    @PostMapping("/cart/checkout")
    public PaymentResponseDTO checkoutCart(@RequestBody PaymentRequestDTO requestDTO) {
        return enrollmentService.checkoutCart(requestDTO);
    }

    // API lấy danh sách khoá học đã đăng ký của user
    @GetMapping("/enrollments")
    public ResponseEntity<List<EnrollmentResponseDTO>> getUserEnrollments(@RequestParam Long userId) {
        List<EnrollmentResponseDTO> enrollments = enrollmentService.getUserEnrollments(userId);
        return ResponseEntity.ok(enrollments);
    }

    // API lấy chi tiết 1 enrollment
    @GetMapping("/enrollments/{enrollmentId}")
    public EnrollmentResponseDTO getEnrollmentDetail(@PathVariable Long enrollmentId) {
        return enrollmentService.getEnrollmentDetail(enrollmentId);
    }

    // API huỷ enrollment
    @PutMapping("/enrollments/{enrollmentId}/cancel")
    public void cancelEnrollment(@PathVariable Long enrollmentId) {
        enrollmentService.cancelEnrollment(enrollmentId);
    }
}
