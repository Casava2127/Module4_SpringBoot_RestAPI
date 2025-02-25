package com.ra.model.dto.payment;

import com.ra.model.entity.PaymentMethod;
import com.ra.model.entity.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor  // 🔹 Thêm annotation này để tạo constructor public
public class PaymentResponseDTO {
    private Long paymentId;
    private Long enrollmentId;
    private String userName;
    private BigDecimal amount;
    private String paymentMethod;
    private String status;
    private LocalDateTime paidAt;
    private LocalDateTime createdAt;


}
