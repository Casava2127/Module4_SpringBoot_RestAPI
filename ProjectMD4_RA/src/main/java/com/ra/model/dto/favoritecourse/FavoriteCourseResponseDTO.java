package com.ra.model.dto.favoritecourse;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavoriteCourseResponseDTO {
    private Long favoriteId;
    private Long courseId;
    private String courseName;
    private LocalDateTime addedAt;
}

