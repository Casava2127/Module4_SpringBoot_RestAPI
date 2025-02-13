package com.ra.service.category.imp;

import com.ra.model.dto.category.CategoryRequestDTO;
import com.ra.model.dto.category.CategoryResponseDTO;
import com.ra.model.entity.Category;
import com.ra.repository.CategoryRepository;
import com.ra.repository.ProductRepository;
import com.ra.service.category.CategoryService01;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImp01 implements CategoryService01 {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponseDTO> findAll() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            return Collections.emptyList();
        }
        return categories.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    @Override
    public CategoryResponseDTO save(CategoryRequestDTO categoryDTO) {
        Category category = convertToEntity(categoryDTO);
        Category savedCategory = categoryRepository.save(category);
        return convertToDTO(savedCategory);
    }

    @Override
    public CategoryResponseDTO update(CategoryRequestDTO categoryDTO) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<CategoryResponseDTO> findById(Long id) {
        return categoryRepository.findById(id).map(this::convertToDTO);
    }

    private CategoryResponseDTO convertToDTO(Category category) {
        return new CategoryResponseDTO(category.getId(), category.getCategoryName());
    }

    private Category convertToEntity(CategoryRequestDTO categoryDTO) {
        Category category = new Category();
        category.setCategoryName(categoryDTO.getCategoryName());
        category.setCategoryStatus(categoryDTO.getCategoryStatus());

        return category;
    }
}
