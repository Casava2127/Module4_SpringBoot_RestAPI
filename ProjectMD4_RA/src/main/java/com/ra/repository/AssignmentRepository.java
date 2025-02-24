package com.ra.repository;

import com.ra.model.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
}
