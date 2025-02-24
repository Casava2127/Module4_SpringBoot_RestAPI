package com.ra.model.dto.course;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CourseRequestDTO {
    @NotNull
    private String sku; // Mã SKU duy nhất cho khóa học

    @NotNull
    private String courseName;

    private String description;

    @NotNull
    private BigDecimal price;

    private int availableSlots;

    private String image;

    private Long categoryId; // ID danh mục

    private Long instructorId; // ID giảng viên
}
