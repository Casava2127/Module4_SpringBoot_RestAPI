package com.ra.model.dto.category;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoryResponseDTO {
    private Long categoryId;
    private String categoryName;
    private String description;
    private boolean categoryStatus;
}
