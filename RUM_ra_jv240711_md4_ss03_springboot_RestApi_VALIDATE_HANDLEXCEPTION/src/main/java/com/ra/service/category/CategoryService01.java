package com.ra.service.category;

import com.ra.model.dto.category.CategoryRequestDTO;
import com.ra.model.dto.category.CategoryResponseDTO;
import com.ra.model.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService01 {
    List<CategoryResponseDTO> findAll();
    //    create
    CategoryResponseDTO save(CategoryRequestDTO categoryDTO);
    //    update
    CategoryResponseDTO update(CategoryRequestDTO categoryDTO);
    //    delete
    void delete(Long id);
    //    find by id
    Optional<CategoryResponseDTO> findById(Long id);
}
