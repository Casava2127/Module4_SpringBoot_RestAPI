package com.ra.model.dto.product;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@AllArgsConstructor // Constructor có tham số
@NoArgsConstructor  // Constructor không tham số
@Getter            // Tự động tạo getter
@Setter            // Tự động tạo setter
@Builder           // Hỗ trợ builder pattern

public class ProductRequestDTO {
    private String productName;
    private Double price; // Giá không được âm
    private String image; // Ảnh có thể null
    private boolean status = true; // Mặc định sản phẩm đang hoạt động
    private Long categoryId; // Chỉ nhận ID của danh mục, không nhận cả Entity
}
