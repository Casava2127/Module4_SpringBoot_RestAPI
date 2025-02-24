package com.ra.service.enrollment;

import com.ra.model.dto.enrollment.EnrollmentRequestDTO;
import com.ra.model.dto.enrollment.EnrollmentResponseDTO;

import java.util.List;
import java.util.Optional;

public interface EnrollmentService {
    List<EnrollmentResponseDTO> findAll();
    Optional<EnrollmentResponseDTO> findById(Long id);
    EnrollmentResponseDTO save(EnrollmentRequestDTO dto);
    EnrollmentResponseDTO update(Long id, EnrollmentRequestDTO dto);
    boolean delete(Long id);
}
