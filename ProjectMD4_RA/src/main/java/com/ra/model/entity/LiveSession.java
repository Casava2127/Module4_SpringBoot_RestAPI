package com.ra.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "live_sessions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LiveSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long liveSessionId;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(name = "session_title", nullable = false, length = 255)
    private String sessionTitle;

    @Column(name = "session_description", columnDefinition = "TEXT")
    private String sessionDescription;

    @Column(name = "scheduled_at", nullable = false)
    private LocalDateTime scheduledAt;

    @Column(name = "duration")
    private Integer duration; // thời lượng tính theo phút

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
