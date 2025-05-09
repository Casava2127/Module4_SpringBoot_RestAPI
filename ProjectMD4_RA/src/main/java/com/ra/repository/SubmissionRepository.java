package com.ra.repository;

import com.ra.model.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    List<Submission> findByAssignmentAssignmentId(Long assignmentId);
    List<Submission> findByUserUserId(Long userId);
}
