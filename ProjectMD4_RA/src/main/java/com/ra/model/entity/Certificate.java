package com.ra.model.entity;

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
    @JoinColumn(name = "enrollment_id", nullable = false)
    private Enrollment enrollment;

    @Column(name = "certificate_url", nullable = false, length = 255)
    private String certificateUrl;

    @Column(name = "issued_at", nullable = false)
    private LocalDateTime issuedAt;
}
