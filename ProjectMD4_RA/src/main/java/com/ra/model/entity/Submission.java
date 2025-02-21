package com.ra.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "submissions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long submissionId;

    @ManyToOne
    @JoinColumn(name = "assignment_id", nullable = false)
    private Assignment assignment;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "file_url", length = 255)
    private String fileUrl;

    @Column(precision = 5, scale = 2)
    private Double grade; // ví dụ từ 0.00 đến 10.00

    @Column(columnDefinition = "TEXT")
    private String feedback;

    @Column(name = "submitted_at", nullable = false)
    private LocalDateTime submittedAt;
}
