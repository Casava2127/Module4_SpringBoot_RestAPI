package com.ra.service.enrollmentDetail.imp;

import com.ra.model.dto.enrollmentDetail.EnrollmentDetailRequestDTO;
import com.ra.model.dto.enrollmentDetail.EnrollmentDetailResponseDTO;
import com.ra.model.entity.Course;
import com.ra.model.entity.Enrollment;
import com.ra.model.entity.EnrollmentDetail;
import com.ra.model.entity.EnrollmentDetailId;
import com.ra.repository.CourseRepository;
import com.ra.repository.EnrollmentDetailRepository;
import com.ra.repository.EnrollmentRepository;
import com.ra.service.enrollmentDetail.EnrollmentDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnrollmentDetailServiceImp implements EnrollmentDetailService {

    @Autowired
    private EnrollmentDetailRepository enrollmentDetailRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<EnrollmentDetailResponseDTO> findAll() {
        return enrollmentDetailRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EnrollmentDetailResponseDTO> findById(Long enrollmentId, Long courseId) {
        EnrollmentDetailId id = new EnrollmentDetailId(enrollmentId, courseId);
        return enrollmentDetailRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    public EnrollmentDetailResponseDTO save(EnrollmentDetailRequestDTO dto) {
        Enrollment enrollment = enrollmentRepository.findById(dto.getEnrollmentId())
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));
        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        EnrollmentDetail detail = EnrollmentDetail.builder()
                .id(new EnrollmentDetailId(dto.getEnrollmentId(), dto.getCourseId()))
                .enrollment(enrollment)
                .course(course)
                .quantity(dto.getQuantity())
                .unitPrice(dto.getUnitPrice())
                .build();

        return convertToDTO(enrollmentDetailRepository.save(detail));
    }

    @Override
    public EnrollmentDetailResponseDTO update(EnrollmentDetailRequestDTO dto) {
        EnrollmentDetailId id = new EnrollmentDetailId(dto.getEnrollmentId(), dto.getCourseId());
        EnrollmentDetail detail = enrollmentDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment detail not found"));

        detail.setQuantity(dto.getQuantity());
        detail.setUnitPrice(dto.getUnitPrice());

        return convertToDTO(enrollmentDetailRepository.save(detail));
    }

    @Override
    public boolean delete(Long enrollmentId, Long courseId) {
        EnrollmentDetailId id = new EnrollmentDetailId(enrollmentId, courseId);
        if (!enrollmentDetailRepository.existsById(id)) return false;
        enrollmentDetailRepository.deleteById(id);
        return true;
    }

    private EnrollmentDetailResponseDTO convertToDTO(EnrollmentDetail detail) {
        return EnrollmentDetailResponseDTO.builder()
                .enrollmentId(detail.getEnrollment().getEnrollmentId())
                .courseId(detail.getCourse().getCourseId())
                .courseName(detail.getCourse().getCourseName())
                .quantity(detail.getQuantity())
                .unitPrice(detail.getUnitPrice())
                .build();
    }
}
