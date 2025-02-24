package com.ra.service.category.imp;

import com.ra.model.dto.category.CategoryRequestDTO;
import com.ra.model.dto.category.CategoryResponseDTO;
import com.ra.model.entity.Category;
import com.ra.repository.CategoryRepository;
import com.ra.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImp implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponseDTO> findAll() {
        return categoryRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CategoryResponseDTO> findById(Long id) {
        return categoryRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public CategoryResponseDTO save(CategoryRequestDTO categoryDTO) {
        Category category = convertToEntity(categoryDTO);
        Category savedCategory = categoryRepository.save(category);
        return convertToDTO(savedCategory);
    }

    @Override
    public CategoryResponseDTO update(Long id, CategoryRequestDTO categoryDTO) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) {
            return null;
        }

        Category existingCategory = optionalCategory.get();
        existingCategory.setCategoryName(categoryDTO.getCategoryName());
        existingCategory.setDescription(categoryDTO.getDescription());
        existingCategory.setCategoryStatus(categoryDTO.isCategoryStatus());

        Category updatedCategory = categoryRepository.save(existingCategory);
        return convertToDTO(updatedCategory);
    }

    @Override
    public boolean delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            return false;
        }
        categoryRepository.deleteById(id);
        return true;
    }

    // ======================= Chuyển đổi Entity ⇆ DTO ========================

    private CategoryResponseDTO convertToDTO(Category category) {
        return CategoryResponseDTO.builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .description(category.getDescription())
                .categoryStatus(category.isCategoryStatus())
                .build();
    }

    private Category convertToEntity(CategoryRequestDTO categoryDTO) {
        return Category.builder()
                .categoryName(categoryDTO.getCategoryName())
                .description(categoryDTO.getDescription())
                .categoryStatus(categoryDTO.isCategoryStatus())
                .build();
    }
}
