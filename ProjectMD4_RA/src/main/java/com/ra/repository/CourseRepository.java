package com.ra.repository;

import com.ra.model.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
