package com.ra.controller;

import com.ra.model.dto.coursereview.CourseReviewRequestDTO;
import com.ra.model.dto.coursereview.CourseReviewResponseDTO;
import com.ra.service.coursereview.CourseReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class CourseReviewController {

    @Autowired
    private CourseReviewService courseReviewService;

    // Thêm đánh giá
    @PostMapping
    public ResponseEntity<CourseReviewResponseDTO> addReview(@RequestBody CourseReviewRequestDTO requestDTO) {
        CourseReviewResponseDTO review = courseReviewService.addReview(requestDTO);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    // Lấy danh sách review theo courseId
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<CourseReviewResponseDTO>> getReviewsByCourse(@PathVariable Long courseId) {
        List<CourseReviewResponseDTO> reviews = courseReviewService.getReviewsByCourse(courseId);
        return ResponseEntity.ok(reviews);
    }

    // Lấy danh sách review theo userId
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CourseReviewResponseDTO>> getReviewsByUser(@PathVariable Long userId) {
        List<CourseReviewResponseDTO> reviews = courseReviewService.getReviewsByUser(userId);
        return ResponseEntity.ok(reviews);
    }

    // Xóa review theo reviewId
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId) {
        boolean success = courseReviewService.deleteReview(reviewId);
        if (success) {
            return ResponseEntity.ok("Review deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Review not found");
    }
}
