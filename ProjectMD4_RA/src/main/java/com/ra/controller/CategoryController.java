package com.ra.controller;

import com.ra.model.dto.category.CategoryResponseDTO;
import com.ra.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping("/api/v1/courses/categories")
@Validated
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    
    @GetMapping()
    public ResponseEntity<List<CategoryResponseDTO>> findAll(){
        List<CategoryResponseDTO> categories = categoryService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO>  findById(@PathVariable Long id){
        Optional<CategoryResponseDTO> category = categoryService.findById(id);
        return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }
}
