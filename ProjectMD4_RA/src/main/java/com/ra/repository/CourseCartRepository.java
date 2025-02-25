package com.ra.repository;

import com.ra.model.entity.CourseCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseCartRepository extends JpaRepository<CourseCart, Long> {
    List<CourseCart> findByUserUserId(Long userId);
    Optional<CourseCart> findByUserUserIdAndCourseCourseId(Long userId, Long courseId);
    void deleteByUserUserId(Long userId);
}
