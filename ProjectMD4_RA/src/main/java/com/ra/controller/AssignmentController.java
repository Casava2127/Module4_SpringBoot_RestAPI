package com.ra.controller;

import com.ra.model.dto.assignment.AssignmentRequestDTO;
import com.ra.model.dto.assignment.AssignmentResponseDTO;
import com.ra.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses/{courseId}/assignments")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;

    @GetMapping
    public ResponseEntity<List<AssignmentResponseDTO>> getAssignments(@PathVariable Long courseId) {
        return ResponseEntity.ok(assignmentService.getAssignmentsByCourseId(courseId));
    }

    @GetMapping("/{assignmentId}")
    public ResponseEntity<AssignmentResponseDTO> getAssignmentDetails(@PathVariable Long assignmentId) {
        return ResponseEntity.ok(assignmentService.getAssignmentDetails(assignmentId));
    }

    @PostMapping
    public ResponseEntity<AssignmentResponseDTO> createAssignment(
            @PathVariable Long courseId,
            @RequestBody AssignmentRequestDTO dto) {
        return ResponseEntity.ok(assignmentService.createAssignment(courseId, dto));
    }

    @PutMapping("/{assignmentId}")
    public ResponseEntity<AssignmentResponseDTO> updateAssignment(
            @PathVariable Long assignmentId,
            @RequestBody AssignmentRequestDTO dto) {
        return ResponseEntity.ok(assignmentService.updateAssignment(assignmentId, dto));
    }

    @DeleteMapping("/{assignmentId}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable Long assignmentId) {
        assignmentService.deleteAssignment(assignmentId);
        return ResponseEntity.noContent().build();
    }
}
