package com.ra.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "courses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @Column(nullable = false, unique = true, length = 100)
    private String sku;

    @Column(name = "course_name", nullable = false, unique = true, length = 100)
    private String courseName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "available_slots", nullable = false)
    private int availableSlots;

    @Column(length = 255)
    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // Giảng viên là User có vai trò INSTRUCTOR
    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private User instructor;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
