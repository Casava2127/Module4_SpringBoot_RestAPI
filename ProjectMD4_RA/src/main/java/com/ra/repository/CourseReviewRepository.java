package com.ra.repository;

import com.ra.model.entity.CourseReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface CourseReviewRepository extends JpaRepository<CourseReview, Long> {
}
