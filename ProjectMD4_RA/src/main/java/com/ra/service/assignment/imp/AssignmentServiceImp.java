package com.ra.service.assignment.imp;

import com.ra.model.dto.assignment.AssignmentRequestDTO;
import com.ra.model.dto.assignment.AssignmentResponseDTO;
import com.ra.model.entity.Assignment;
import com.ra.model.entity.Course;
import com.ra.repository.AssignmentRepository;
import com.ra.repository.CourseRepository;
import com.ra.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssignmentServiceImp implements AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final CourseRepository courseRepository;

    @Override
    public List<AssignmentResponseDTO> getAssignmentsByCourseId(Long courseId) {
        return assignmentRepository.findByCourse_CourseId(courseId)
                .stream()
                .map(assignment -> new AssignmentResponseDTO(
                        assignment.getAssignmentId(),
                        assignment.getTitle(),
                        assignment.getDescription(),
                        assignment.getDueDate(),
                        assignment.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public AssignmentResponseDTO getAssignmentDetails(Long assignmentId) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));
        return new AssignmentResponseDTO(
                assignment.getAssignmentId(),
                assignment.getTitle(),
                assignment.getDescription(),
                assignment.getDueDate(),
                assignment.getCreatedAt()
        );
    }

    @Override
    public AssignmentResponseDTO createAssignment(Long courseId, AssignmentRequestDTO dto) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Assignment assignment = Assignment.builder()
                .course(course)
                .title(dto.getTitle())
                .description(dto.getDescription())
                .dueDate(dto.getDueDate())
                .build();

        Assignment savedAssignment = assignmentRepository.save(assignment);

        return new AssignmentResponseDTO(
                savedAssignment.getAssignmentId(),
                savedAssignment.getTitle(),
                savedAssignment.getDescription(),
                savedAssignment.getDueDate(),
                savedAssignment.getCreatedAt()
        );
    }

    @Override
    public AssignmentResponseDTO updateAssignment(Long assignmentId, AssignmentRequestDTO dto) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));

        assignment.setTitle(dto.getTitle());
        assignment.setDescription(dto.getDescription());
        assignment.setDueDate(dto.getDueDate());

        Assignment updatedAssignment = assignmentRepository.save(assignment);

        return new AssignmentResponseDTO(
                updatedAssignment.getAssignmentId(),
                updatedAssignment.getTitle(),
                updatedAssignment.getDescription(),
                updatedAssignment.getDueDate(),
                updatedAssignment.getCreatedAt()
        );
    }

    @Override
    public void deleteAssignment(Long assignmentId) {
        assignmentRepository.deleteById(assignmentId);
    }
}
