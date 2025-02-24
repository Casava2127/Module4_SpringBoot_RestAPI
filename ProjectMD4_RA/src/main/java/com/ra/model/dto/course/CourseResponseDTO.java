package com.ra.model.dto.course;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CourseResponseDTO {
    private Long courseId;
    private String sku;
    private String courseName;
    private String description;
    private BigDecimal price;
    private int availableSlots;
    private String image;
    private Long categoryId;
    private String categoryName; // Trả về tên danh mục
    private Long instructorId;
    private String instructorName; // Trả về tên giảng viên
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
