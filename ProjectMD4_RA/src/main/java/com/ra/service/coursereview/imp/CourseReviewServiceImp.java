package com.ra.service.coursereview.imp;

import com.ra.model.dto.coursereview.CourseReviewRequestDTO;
import com.ra.model.dto.coursereview.CourseReviewResponseDTO;
import com.ra.model.entity.Course;
import com.ra.model.entity.CourseReview;
import com.ra.model.entity.User;
import com.ra.repository.CourseRepository;
import com.ra.repository.CourseReviewRepository;
import com.ra.repository.UserRepository;
import com.ra.service.coursereview.CourseReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseReviewServiceImp implements CourseReviewService {

    @Autowired
    private CourseReviewRepository courseReviewRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CourseReviewResponseDTO addReview(CourseReviewRequestDTO requestDTO) {
        // Kiểm tra xem khóa học và user có tồn tại không
        Course course = courseRepository.findById(requestDTO.getCourseId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));

        User user = userRepository.findById(requestDTO.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        // Tạo đối tượng CourseReview
        CourseReview review = CourseReview.builder()
                .course(course)
                .user(user)
                .rating(requestDTO.getRating())
                .comment(requestDTO.getComment())
                .createdAt(LocalDateTime.now())
                .build();

        // Lưu vào database
        CourseReview savedReview = courseReviewRepository.save(review);
        return convertToDTO(savedReview);
    }

    @Override
    public List<CourseReviewResponseDTO> getReviewsByCourse(Long courseId) {
        List<CourseReview> reviews = courseReviewRepository.findByCourseCourseId(courseId);
        return reviews.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<CourseReviewResponseDTO> getReviewsByUser(Long userId) {
        List<CourseReview> reviews = courseReviewRepository.findByUserUserId(userId);
        return reviews.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public boolean deleteReview(Long reviewId) {
        if (!courseReviewRepository.existsById(reviewId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found");
        }
        courseReviewRepository.deleteById(reviewId);
        return true;
    }

    private CourseReviewResponseDTO convertToDTO(CourseReview review) {
        return CourseReviewResponseDTO.builder()
                .reviewId(review.getReviewId())
                .courseId(review.getCourse().getCourseId())
                .courseName(review.getCourse().getCourseName())
                .userId(review.getUser().getUserId())
                .userName(review.getUser().getUsername())
                .rating(review.getRating())
                .comment(review.getComment())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
