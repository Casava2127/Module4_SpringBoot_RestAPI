package com.ra.model.dto.courseLesson;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CourseLessonRequestDTO {
    private Long courseId;
    private String lessonTitle;
    private String content;
    private String videoUrl;
    private int sortOrder;
}
