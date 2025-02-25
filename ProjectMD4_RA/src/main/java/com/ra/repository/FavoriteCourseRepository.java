package com.ra.repository;

import com.ra.model.entity.FavoriteCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface FavoriteCourseRepository extends JpaRepository<FavoriteCourse, Long> {
    Optional<FavoriteCourse> findByUserUserIdAndCourseCourseId(Long userId, Long courseId);
    List<FavoriteCourse> findByUserUserId(Long userId);
}
