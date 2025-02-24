package com.ra.model.dto.category;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoryRequestDTO {
    @NotNull
    private String categoryName;
    private String description;
    private boolean categoryStatus = true; // Mặc định là Active
}
