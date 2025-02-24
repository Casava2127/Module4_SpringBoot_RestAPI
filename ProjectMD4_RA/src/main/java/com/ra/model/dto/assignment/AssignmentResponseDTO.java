package com.ra.model.dto.assignment;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignmentResponseDTO {
    private Long assignmentId;
    private String courseName;
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private LocalDateTime createdAt;
}
