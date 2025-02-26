package com.ra.service.submission.imp;


import com.ra.exception.ResourceNotFoundException;
import com.ra.model.dto.submission.SubmissionRequestDTO;
import com.ra.model.dto.submission.SubmissionResponseDTO;
import com.ra.model.entity.Assignment;
import com.ra.model.entity.Submission;
import com.ra.model.entity.User;
import com.ra.repository.AssignmentRepository;
import com.ra.repository.SubmissionRepository;
import com.ra.repository.UserRepository;
import com.ra.service.submission.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubmissionServiceImp implements SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<SubmissionResponseDTO> getSubmissionsByAssignment(Long assignmentId) {
        return submissionRepository.findByAssignmentAssignmentId(assignmentId)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SubmissionResponseDTO submitAssignment(Long userId, SubmissionRequestDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Assignment assignment = assignmentRepository.findById(dto.getAssignmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found"));

        Submission submission = Submission.builder()
                .user(user)
                .assignment(assignment)
                .fileUrl(dto.getFileUrl())
                .submittedAt(LocalDateTime.now())
                .build();

        submissionRepository.save(submission);
        return mapToResponseDTO(submission);
    }

    @Override
    public SubmissionResponseDTO updateSubmission(Long userId, Long submissionId, SubmissionRequestDTO dto) {
        Submission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new ResourceNotFoundException("Submission not found"));

        if (!submission.getUser().getUserId().equals(userId)) {
            throw new RuntimeException("You can only update your own submission");
        }

        submission.setFileUrl(dto.getFileUrl());
        submissionRepository.save(submission);

        return mapToResponseDTO(submission);
    }

    private SubmissionResponseDTO mapToResponseDTO(Submission submission) {
        return SubmissionResponseDTO.builder()
                .submissionId(submission.getSubmissionId())
                .studentName(submission.getUser().getFullname())
                .fileUrl(submission.getFileUrl())
                .grade(submission.getGrade())
                .feedback(submission.getFeedback())
                .submittedAt(submission.getSubmittedAt())
                .build();
    }
}
