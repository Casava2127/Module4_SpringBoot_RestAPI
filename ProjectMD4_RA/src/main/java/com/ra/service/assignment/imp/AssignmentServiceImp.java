package com.ra.service.assignment.imp;

import com.ra.model.dto.assignment.AssignmentRequestDTO;
import com.ra.model.dto.assignment.AssignmentResponseDTO;
import com.ra.model.entity.Assignment;
import com.ra.model.entity.Course;
import com.ra.repository.AssignmentRepository;
import com.ra.repository.CourseRepository;
import com.ra.service.assignment.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssignmentServiceImp implements AssignmentService {
    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<AssignmentResponseDTO> findAll() {
        return assignmentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AssignmentResponseDTO> findById(Long id) {
        return assignmentRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public AssignmentResponseDTO save(AssignmentRequestDTO assignmentDTO) {
        Assignment assignment = convertToEntity(assignmentDTO);
        Assignment savedAssignment = assignmentRepository.save(assignment);
        return convertToDTO(savedAssignment);
    }

    @Override
    public AssignmentResponseDTO update(Long id, AssignmentRequestDTO assignmentDTO) {
        Optional<Assignment> optionalAssignment = assignmentRepository.findById(id);
        if (!optionalAssignment.isPresent()) return null;

        Assignment assignment = optionalAssignment.get();
        assignment.setTitle(assignmentDTO.getTitle());
        assignment.setDescription(assignmentDTO.getDescription());
        assignment.setDueDate(assignmentDTO.getDueDate());

        Assignment updatedAssignment = assignmentRepository.save(assignment);
        return convertToDTO(updatedAssignment);
    }

    @Override
    public boolean delete(Long id) {
        if (!assignmentRepository.existsById(id)) return false;
        assignmentRepository.deleteById(id);
        return true;
    }

    private AssignmentResponseDTO convertToDTO(Assignment assignment) {
        return AssignmentResponseDTO.builder()
                .assignmentId(assignment.getAssignmentId())
                .courseName(assignment.getCourse().getCourseName())
                .title(assignment.getTitle())
                .description(assignment.getDescription())
                .dueDate(assignment.getDueDate())
                .createdAt(assignment.getCreatedAt())
                .build();
    }

    private Assignment convertToEntity(AssignmentRequestDTO assignmentDTO) {
        Course course = courseRepository.findById(assignmentDTO.getCourseId()).orElse(null);

        return Assignment.builder()
                .course(course)
                .title(assignmentDTO.getTitle())
                .description(assignmentDTO.getDescription())
                .dueDate(assignmentDTO.getDueDate())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
