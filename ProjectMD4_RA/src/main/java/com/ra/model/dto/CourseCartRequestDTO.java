package com.ra.model.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseCartRequestDTO {
    private Long courseId;
    private int quantity;
}
