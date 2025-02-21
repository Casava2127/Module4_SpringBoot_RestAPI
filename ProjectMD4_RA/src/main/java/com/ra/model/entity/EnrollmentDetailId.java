package com.ra.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class EnrollmentDetailId implements Serializable {
    private Long enrollmentId;
    private Long courseId;
}
