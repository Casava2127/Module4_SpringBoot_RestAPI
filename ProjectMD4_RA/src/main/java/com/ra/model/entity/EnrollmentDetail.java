package com.ra.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "enrollment_details")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EnrollmentDetail {
    @EmbeddedId
    private EnrollmentDetailId id;

    @ManyToOne
    @MapsId("enrollmentId")
    @JoinColumn(name = "enrollment_id")
    private Enrollment enrollment;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "course_name", length = 100)
    private String courseName;

    @Column(name = "unit_price", precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(nullable = false)
    private int quantity;
}
