package com.ra.model.dto.enrollment;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollmentResponseDTO {
    private Long enrollmentId;
    private String serialNumber;
    private BigDecimal totalPrice;
    private String status;
    private LocalDateTime createdAt;
}
