package com.ra.service.enrollmentDetail;

import com.ra.model.dto.enrollmentDetail.EnrollmentDetailRequestDTO;
import com.ra.model.dto.enrollmentDetail.EnrollmentDetailResponseDTO;

import java.util.List;
import java.util.Optional;

public interface EnrollmentDetailService {
    List<EnrollmentDetailResponseDTO> findAll();
    Optional<EnrollmentDetailResponseDTO> findById(Long enrollmentId, Long courseId);
    EnrollmentDetailResponseDTO save(EnrollmentDetailRequestDTO dto);
    EnrollmentDetailResponseDTO update(EnrollmentDetailRequestDTO dto);
    boolean delete(Long enrollmentId, Long courseId);
}
