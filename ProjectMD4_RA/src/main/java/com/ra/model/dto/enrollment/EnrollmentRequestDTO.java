package com.ra.model.dto.enrollment;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EnrollmentRequestDTO {
    @NotNull
    private Long userId; // ID của người đăng ký

    @NotNull
    private BigDecimal totalPrice;

    @NotNull
    private String status; // Trạng thái đăng ký (WAITING, CONFIRMED, IN_PROGRESS, COMPLETED, CANCELED, DENIED)

    private String note;
}
