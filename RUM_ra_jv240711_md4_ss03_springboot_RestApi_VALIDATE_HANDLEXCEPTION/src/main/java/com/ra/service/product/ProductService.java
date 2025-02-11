package com.ra.service.product;

import com.ra.model.dto.product.ProductResponseDTO;
import com.ra.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<ProductResponseDTO> findAll();
    Product save(Product product);
//    Product update(Product product);
    void delete(Long id);
    Product findById(Long id);
}
