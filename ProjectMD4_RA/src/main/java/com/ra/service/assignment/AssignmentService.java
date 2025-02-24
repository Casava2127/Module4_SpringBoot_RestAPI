package com.ra.service.assignment;

import com.ra.model.dto.assignment.AssignmentRequestDTO;
import com.ra.model.dto.assignment.AssignmentResponseDTO;

import java.util.List;
import java.util.Optional;

public interface AssignmentService {
    List<AssignmentResponseDTO> findAll();
    Optional<AssignmentResponseDTO> findById(Long id);
    AssignmentResponseDTO save(AssignmentRequestDTO assignmentDTO);
    AssignmentResponseDTO update(Long id, AssignmentRequestDTO assignmentDTO);
    boolean delete(Long id);
}
