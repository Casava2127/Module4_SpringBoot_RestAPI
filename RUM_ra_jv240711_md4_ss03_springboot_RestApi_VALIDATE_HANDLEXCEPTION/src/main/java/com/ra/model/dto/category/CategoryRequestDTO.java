package com.ra.model.dto.category;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@AllArgsConstructor // Constructor có tham số
@NoArgsConstructor  // Constructor không tham số
@Getter            // Tự động tạo getter
@Setter            // Tự động tạo setter
@Builder
public class CategoryRequestDTO {
    @NotNull
    private String categoryName;
    private boolean categoryStatus = true;

    public boolean getCategoryStatus() {
        return categoryStatus;
    }
}
