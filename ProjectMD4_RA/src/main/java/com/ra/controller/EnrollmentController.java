package com.ra.controller;

import com.ra.model.dto.enrollment.EnrollmentResponseDTO;
import com.ra.model.dto.enrollmentDetail.EnrollmentDetailResponseDTO;
import com.ra.model.dto.payment.PaymentRequestDTO;
import com.ra.model.dto.payment.PaymentResponseDTO;
import com.ra.service.enrollment.EnrollmentService;
import com.ra.service.enrollmentDetail.EnrollmentDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;
    private final EnrollmentDetailService enrollmentDetailService;

    public EnrollmentController(EnrollmentService enrollmentService, EnrollmentDetailService enrollmentDetailService) {
        this.enrollmentService = enrollmentService;
        this.enrollmentDetailService = enrollmentDetailService;
    }

    // API checkout cart
    @PostMapping("/cart/checkoutEnrollment")
    public ResponseEntity<?> checkoutCart(@RequestBody PaymentRequestDTO requestDTO) {
        try {
            PaymentResponseDTO response = enrollmentService.checkoutCart(requestDTO);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            // In lỗi ra console
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred: " + ex.getMessage());
        }
    }

//    // API lấy danh sách khoá học đã đăng ký của user
//    @GetMapping("/enrollments")
//    public ResponseEntity<List<EnrollmentResponseDTO>> getUserEnrollments(@RequestParam Long userId) {
//        List<EnrollmentResponseDTO> enrollments = enrollmentService.getUserEnrollments(userId);
//        return ResponseEntity.ok(enrollments);
//    }

    // API lấy chi tiết 1 enrollment
    @GetMapping("/enrollments/{enrollmentId}")
    public List<EnrollmentDetailResponseDTO> getEnrollmentDetail(@PathVariable Long enrollmentId) {
        return enrollmentDetailService.getEnrollmentDetail(enrollmentId);
    }

    // API huỷ enrollment
    @PutMapping("/enrollments/{enrollmentId}/cancel")
    public void cancelEnrollment(@PathVariable Long enrollmentId) {
        enrollmentService.cancelEnrollment(enrollmentId);
    }
}
