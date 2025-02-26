package com.ra.repository;

import com.ra.model.entity.CourseReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseReviewRepository extends JpaRepository<CourseReview, Long> {
    List<CourseReview> findByCourseCourseId(Long courseId);
    List<CourseReview> findByUserUserId(Long userId);
}
