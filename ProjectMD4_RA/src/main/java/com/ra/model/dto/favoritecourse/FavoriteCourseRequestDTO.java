package com.ra.model.dto.favoritecourse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavoriteCourseRequestDTO {
    private Long userId;
    private Long courseId;
}