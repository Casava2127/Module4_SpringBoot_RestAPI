package com.ra.service.favoritecourse;

import com.ra.model.dto.favoritecourse.FavoriteCourseResponseDTO;
import java.util.List;

public interface FavoriteCourseService {
    FavoriteCourseResponseDTO addFavorite(Long userId, Long courseId);
    boolean removeFavoriteById(Long favoriteId);
    List<FavoriteCourseResponseDTO> getFavoriteCourses(Long userId);
}
