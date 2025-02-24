package com.ra.model.dto.courseLesson;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CourseLessonResponseDTO {
    private Long lessonId;
    private String lessonTitle;
    private String content;
    private String videoUrl;
    private int sortOrder;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
