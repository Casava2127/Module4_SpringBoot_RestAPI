package com.ra.model.dto.enrollmentDetail;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EnrollmentDetailRequestDTO {
    private Long enrollmentId;
    private Long courseId;
    private int quantity;
    private BigDecimal unitPrice;
}
