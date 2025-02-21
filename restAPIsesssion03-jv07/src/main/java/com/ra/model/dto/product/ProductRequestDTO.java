package com.ra.model.dto.product;

import com.ra.validate.ProductUnique;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductRequestDTO {
    @NotBlank(message = "Không được rỗng")
    @ProductUnique(message = "Tên sản phẩm đã tồn tại")
    private String productName;
    @NotNull(message = "giá ko rỗng")
    @Min(value = 0,message = "gia phai lon hon 0")
    private Double price;
    private MultipartFile image;
    private Boolean status;
    private Long categoryId;
}
