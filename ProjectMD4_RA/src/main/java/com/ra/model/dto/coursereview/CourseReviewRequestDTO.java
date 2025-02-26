package com.ra.model.dto.coursereview;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseReviewRequestDTO {
    private Long courseId;
    private Long userId;

    @Min(1)
    @Max(5)
    private int rating;

    private String comment;
}
