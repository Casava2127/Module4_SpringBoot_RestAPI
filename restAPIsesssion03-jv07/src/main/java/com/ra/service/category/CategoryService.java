package com.ra.service.category;

import com.ra.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    Page<Category> findAll(Pageable pageable);
    Category save(Category category);
    Category findById(Long id);
    void delete(Long id);
}
