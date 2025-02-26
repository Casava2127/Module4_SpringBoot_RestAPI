package com.ra.model.dto.submission;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmissionRequestDTO {
    private Long assignmentId;
    private String fileUrl;  // Đường dẫn file bài nộp
}
