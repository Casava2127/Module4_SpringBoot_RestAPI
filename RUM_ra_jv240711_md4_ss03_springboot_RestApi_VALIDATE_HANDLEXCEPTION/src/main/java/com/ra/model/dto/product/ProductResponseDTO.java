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
//Nếu ProductResponseDTO có 5 trường nhưng trong phương thức convertToDTO() bạn chỉ ánh xạ 3 trường, thì:
//
//3 trường được ánh xạ → Nhận giá trị từ convertToDTO().
//2 trường còn lại → Nếu không được truyền giá trị, chúng sẽ có giá trị mặc định:
//Nếu là kiểu object (String, Double, ...) → Mặc định là null.
//Nếu là kiểu primitive (int, double, boolean, ...) → Mặc định theo kiểu (0, false, ...).