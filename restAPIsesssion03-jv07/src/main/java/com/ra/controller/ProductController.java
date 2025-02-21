package com.ra.controller;

import com.ra.model.dto.product.ProductRequestDTO;
import com.ra.model.dto.product.ProductResponseDTO;
import com.ra.model.entity.Product;
import com.ra.service.product.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAll(){
        List<ProductResponseDTO> products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/paginate")
    public ResponseEntity<Page<ProductResponseDTO>> getAllPaginate(
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "limit",defaultValue = "1") int limit,
            @RequestParam(name = "sortBy",defaultValue = "price") String sortBy,
            @RequestParam(name = "orderBy",defaultValue = "asc") String orderBy){
        System.out.println(limit);
        Sort sort = orderBy.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page,limit,sort);
        Page<ProductResponseDTO> products = productService.pagination(pageable);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@Valid @ModelAttribute ProductRequestDTO productRequestDTO){
        ProductResponseDTO productResponseDTO = productService.save(productRequestDTO);
        return new ResponseEntity<>(productResponseDTO,HttpStatus.CREATED);
    }
}
