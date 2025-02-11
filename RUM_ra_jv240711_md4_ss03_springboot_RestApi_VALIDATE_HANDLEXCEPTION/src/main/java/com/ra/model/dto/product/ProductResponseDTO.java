package com.ra.model.dto.product;

import jakarta.persistence.Column;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class ProductResponseDTO {
    private Long id;
    private String productName;
    private double price;
    private String image;
    private boolean status;
    private String categoryName;
}
