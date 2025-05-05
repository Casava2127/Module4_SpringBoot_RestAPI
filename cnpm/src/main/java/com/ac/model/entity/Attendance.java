package com.ac.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "attendance")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendanceId;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne
    @JoinColumn(name = "qr_id", nullable = false)
    private QRCode qrCode;

    @Enumerated(EnumType.STRING)
    private AttendanceStatus status = AttendanceStatus.PENDING;

    @Column(nullable = false, updatable = false)
    private LocalDateTime scanTime = LocalDateTime.now();
}
