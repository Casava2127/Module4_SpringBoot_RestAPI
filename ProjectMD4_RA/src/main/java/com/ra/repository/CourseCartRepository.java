package com.ra.repository;

import com.ra.model.entity.CourseCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface CourseCartRepository extends JpaRepository<CourseCart, Long> {
}
