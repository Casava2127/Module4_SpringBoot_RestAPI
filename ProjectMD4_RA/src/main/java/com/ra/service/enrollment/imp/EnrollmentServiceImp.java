package com.ra.service.enrollment.imp;

import com.ra.model.dto.enrollment.EnrollmentRequestDTO;
import com.ra.model.dto.enrollment.EnrollmentResponseDTO;
import com.ra.model.entity.Enrollment;
import com.ra.model.entity.EnrollmentStatus;
import com.ra.model.entity.User;
import com.ra.repository.EnrollmentRepository;
import com.ra.repository.UserRepository;
import com.ra.service.enrollment.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnrollmentServiceImp implements EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<EnrollmentResponseDTO> findAll() {
        return enrollmentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EnrollmentResponseDTO> findById(Long id) {
        return enrollmentRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    public EnrollmentResponseDTO save(EnrollmentRequestDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Enrollment enrollment = Enrollment.builder()
                .serialNumber("ENR-" + System.currentTimeMillis())
                .user(user)
                .totalPrice(dto.getTotalPrice())
                .status(EnrollmentStatus.valueOf(dto.getStatus()))
                .note(dto.getNote())
                .build();

        return convertToDTO(enrollmentRepository.save(enrollment));
    }

    @Override
    public EnrollmentResponseDTO update(Long id, EnrollmentRequestDTO dto) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        enrollment.setTotalPrice(dto.getTotalPrice());
        enrollment.setStatus(EnrollmentStatus.valueOf(dto.getStatus()));
        enrollment.setNote(dto.getNote());

        return convertToDTO(enrollmentRepository.save(enrollment));
    }

    @Override
    public boolean delete(Long id) {
        if (!enrollmentRepository.existsById(id)) return false;
        enrollmentRepository.deleteById(id);
        return true;
    }

    private EnrollmentResponseDTO convertToDTO(Enrollment enrollment) {
        return EnrollmentResponseDTO.builder()
                .enrollmentId(enrollment.getEnrollmentId())
                .serialNumber(enrollment.getSerialNumber())
                .username(enrollment.getUser().getFullname())
                .totalPrice(enrollment.getTotalPrice())
                .status(enrollment.getStatus().toString())
                .note(enrollment.getNote())
                .build();
    }
}
