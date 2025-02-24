package com.ra.model.dto.enrollment;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EnrollmentResponseDTO {
    private Long enrollmentId;
    private String serialNumber;
    private Long userId;
    private String username; // Trả về tên người dùng
    private BigDecimal totalPrice;
    private String status;
    private String note;
    private LocalDateTime createdAt;
}
