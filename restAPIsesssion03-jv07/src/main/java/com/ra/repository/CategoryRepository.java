package com.ra.repository;

import com.ra.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;

public interface CategoryRepository extends JpaRepository<Category,Long> {

}
