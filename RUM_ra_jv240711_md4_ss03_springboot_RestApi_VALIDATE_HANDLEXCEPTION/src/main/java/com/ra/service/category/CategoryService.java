package com.ra.service.category;

import com.ra.model.entity.Category;
import java.util.List;

public interface CategoryService {
    List<Category> findAll();
//    create
    Category save(Category category);
//    update
    Category update(Category category);
//    delete
    void delete(Long id);
//    find by id
    Category findById(Long id);
}
