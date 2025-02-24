package com.ra.model.dto.enrollment;

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
    private BigDecimal unitPrice;
    private int quantity;
}
