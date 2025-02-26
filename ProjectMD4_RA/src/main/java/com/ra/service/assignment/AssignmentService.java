package com.ra.service;

import com.ra.model.dto.assignment.AssignmentRequestDTO;
import com.ra.model.dto.assignment.AssignmentResponseDTO;

import java.util.List;

public interface AssignmentService {
    List<AssignmentResponseDTO> getAssignmentsByCourseId(Long courseId);
    AssignmentResponseDTO getAssignmentDetails(Long assignmentId);
    AssignmentResponseDTO createAssignment(Long courseId, AssignmentRequestDTO dto);
    AssignmentResponseDTO updateAssignment(Long assignmentId, AssignmentRequestDTO dto);
    void deleteAssignment(Long assignmentId);
}
