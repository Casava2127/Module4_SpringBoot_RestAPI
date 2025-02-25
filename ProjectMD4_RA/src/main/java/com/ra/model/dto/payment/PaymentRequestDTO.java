package com.ra.model.dto.payment;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class PaymentRequestDTO {
    private Long enrollmentId;  // ID của đơn đăng ký khóa học
    private Long userId;        // ID của người dùng
    private BigDecimal amount;  // Số tiền thanh toán
    private String paymentMethod; // Phương thức thanh toán (e.g., "Credit Card", "PayPal")
    private String status;       // Trạng thái thanh toán (e.g., "PENDING", "COMPLETED")
    private LocalDateTime paidAt; // Thời gian thanh toán
}
