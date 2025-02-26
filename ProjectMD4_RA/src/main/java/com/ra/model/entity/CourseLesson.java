package com.ra.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "course_lessons")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CourseLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lessonId;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(name = "lesson_title", nullable = false, length = 255)
    private String lessonTitle;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "video_url", length = 255)
    private String videoUrl;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
