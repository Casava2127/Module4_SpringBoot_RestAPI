package com.ra.repository;

import com.ra.model.entity.CourseLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface CourseLessonRepository extends JpaRepository<CourseLesson, Long> {
}
