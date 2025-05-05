package com.ra.controller;

import com.ra.model.dto.product.ProductRequestDTO;
import com.ra.model.dto.product.ProductResponseDTO;
import com.ra.service.product.ProductService01;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
@Validated // Hỗ trợ @Valid cho RequestDTO
public class ProductController01 {
    @Autowired
    private ProductService01 productService01;

    // Lấy danh sách tất cả sản phẩm
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAll() {
        List<ProductResponseDTO> products = productService01.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Tạo mới sản phẩm (Nhận ProductRequestDTO, Trả về ProductResponseDTO)
    @PostMapping("/create")
    public ResponseEntity<ProductResponseDTO> create(@RequestBody @Validated ProductRequestDTO productDTO) {
        ProductResponseDTO newProduct = productService01.save(productDTO);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    // Cập nhật sản phẩm (Nhận ProductRequestDTO, Trả về ProductResponseDTO)
    @PutMapping("/update/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable Long id, @RequestBody @Validated ProductRequestDTO productDTO) {
        ProductResponseDTO updatedProduct = productService01.update(id, productDTO);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    // Xóa sản phẩm
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService01.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Lấy sản phẩm theo ID
    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<ProductResponseDTO>> getById(@PathVariable Long id) {
        Optional<ProductResponseDTO> product = productService01.findById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
