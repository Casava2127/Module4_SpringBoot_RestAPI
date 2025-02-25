package com.ra.model.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseCartResponseDTO {
    private Long cartId;
    private Long courseId;
    private String courseName;
    private int quantity;
    private String courseImage;
    private double price;
}
