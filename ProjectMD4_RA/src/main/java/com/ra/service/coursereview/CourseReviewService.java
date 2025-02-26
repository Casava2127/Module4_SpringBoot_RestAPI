package com.ra.service.coursereview;

import com.ra.model.dto.coursereview.CourseReviewRequestDTO;
import com.ra.model.dto.coursereview.CourseReviewResponseDTO;
import java.util.List;

public interface CourseReviewService {
    CourseReviewResponseDTO addReview(CourseReviewRequestDTO requestDTO);
    List<CourseReviewResponseDTO> getReviewsByCourse(Long courseId);
    List<CourseReviewResponseDTO> getReviewsByUser(Long userId);
    boolean deleteReview(Long reviewId);
}
