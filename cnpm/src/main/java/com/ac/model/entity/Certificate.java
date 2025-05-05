package com.ac.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "certificates")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long certificateId;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(nullable = false, updatable = false)
    private LocalDateTime issueDate = LocalDateTime.now();
}
