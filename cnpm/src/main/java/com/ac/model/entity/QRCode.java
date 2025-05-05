package com.ac.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "qr_codes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class QRCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qrId;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(nullable = false, unique = true, length = 255)
    private String qrCode;

    @Column(nullable = false)
    private LocalDateTime validFrom;

    @Column(nullable = false)
    private LocalDateTime validUntil;
}
