package com.ra.model.dto.enrollmentDetail;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EnrollmentDetailResponseDTO {
    private Long enrollmentId;
    private Long courseId;
    private String courseName;
    private int quantity;
    private BigDecimal unitPrice;
}
