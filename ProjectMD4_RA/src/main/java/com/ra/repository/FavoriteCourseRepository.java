package com.ra.repository;

import com.ra.model.entity.FavoriteCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface FavoriteCourseRepository extends JpaRepository<FavoriteCourse, Long> {
}
