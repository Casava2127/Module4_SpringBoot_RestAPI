package com.ra.controller;

import com.ra.model.dto.submission.SubmissionRequestDTO;
import com.ra.model.dto.submission.SubmissionResponseDTO;
import com.ra.service.submission.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses/{courseId}/assignments/{assignmentId}/submissions")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @GetMapping
    public ResponseEntity<List<SubmissionResponseDTO>> getSubmissions(@PathVariable Long assignmentId) {
        return ResponseEntity.ok(submissionService.getSubmissionsByAssignment(assignmentId));
    }

    @PostMapping
    public ResponseEntity<SubmissionResponseDTO> submitAssignment(
            @RequestParam Long userId,
            @RequestBody SubmissionRequestDTO requestDTO) {
        return ResponseEntity.ok(submissionService.submitAssignment(userId, requestDTO));
    }

    @PutMapping("/{submissionId}")
    public ResponseEntity<SubmissionResponseDTO> updateSubmission(
            @RequestParam Long userId,
            @PathVariable Long submissionId,
            @RequestBody SubmissionRequestDTO requestDTO) {
        return ResponseEntity.ok(submissionService.updateSubmission(userId, submissionId, requestDTO));
    }
}
