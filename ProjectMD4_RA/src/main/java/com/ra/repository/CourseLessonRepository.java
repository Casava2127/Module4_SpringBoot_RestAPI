package com.ra.repository;

import com.ra.model.entity.CourseLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CourseLessonRepository extends JpaRepository<CourseLesson, Long> {
    List<CourseLesson> findByCourseCourseId(Long courseId);
}
