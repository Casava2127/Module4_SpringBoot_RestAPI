package com.ra.service.submission;

import com.ra.model.dto.submission.SubmissionRequestDTO;
import com.ra.model.dto.submission.SubmissionResponseDTO;

import java.util.List;

public interface SubmissionService {
    List<SubmissionResponseDTO> getSubmissionsByAssignment(Long assignmentId);
    SubmissionResponseDTO submitAssignment(Long userId, SubmissionRequestDTO dto);
    SubmissionResponseDTO updateSubmission(Long userId, Long submissionId, SubmissionRequestDTO dto);
}
