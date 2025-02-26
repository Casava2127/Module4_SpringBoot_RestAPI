package com.ra.model.dto.submission;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmissionResponseDTO {
    private Long submissionId;
    private String studentName;
    private String fileUrl;
    private Double grade;
    private String feedback;
    private LocalDateTime submittedAt;
}
