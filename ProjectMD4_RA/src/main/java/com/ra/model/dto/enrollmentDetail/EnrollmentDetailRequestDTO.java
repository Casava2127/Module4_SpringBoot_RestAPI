package com.ra.model.dto.enrollmentDetail;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EnrollmentDetailRequestDTO {
    @NotNull
    private Long enrollmentId; // ID của đăng ký khóa học

    @NotNull
    private Long courseId; // ID của khóa học

    private String courseName; // Tên khóa học (tùy chọn, có thể lấy từ DB)

    @NotNull
    private BigDecimal unitPrice; // Giá của khóa học tại thời điểm đăng ký

    @NotNull
    private int quantity; // Số lượng (thường là 1)
}
