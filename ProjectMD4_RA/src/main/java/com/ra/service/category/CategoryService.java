package com.ra.service.category;

import com.ra.model.dto.category.CategoryRequestDTO;
import com.ra.model.dto.category.CategoryResponseDTO;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<CategoryResponseDTO> findAll();
    Optional<CategoryResponseDTO> findById(Long id);
    CategoryResponseDTO save(CategoryRequestDTO categoryDTO);
    CategoryResponseDTO update(Long id, CategoryRequestDTO categoryDTO);
    boolean delete(Long id);
}
