package com.ra.model.dto.category;

import org.antlr.v4.runtime.misc.NotNull;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@AllArgsConstructor // Constructor có tham số
@NoArgsConstructor  // Constructor không tham số
@Getter            // Tự động tạo getter
@Setter            // Tự động tạo setter
@Builder

public class CategoryResponseDTO {
    private Long id;
    private String categoryName;
}
