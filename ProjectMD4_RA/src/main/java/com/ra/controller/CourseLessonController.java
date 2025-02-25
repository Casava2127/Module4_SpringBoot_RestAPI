package com.ra.controller;

import com.ra.model.dto.courseLesson.CourseLessonRequestDTO;
import com.ra.model.dto.courseLesson.CourseLessonResponseDTO;
import com.ra.service.courseLesson.CourseLessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/courses/{courseId}/lessons")
@Validated
public class CourseLessonController {

    @Autowired
    private CourseLessonService courseLessonService;

    // Lấy danh sách bài giảng của một khóa học
    @GetMapping
    public ResponseEntity<List<CourseLessonResponseDTO>> getLessons(@PathVariable Long courseId) {
        List<CourseLessonResponseDTO> lessons = courseLessonService.getLessonsByCourseId(courseId);
        return new ResponseEntity<>(lessons, HttpStatus.OK);
    }

    // Lấy thông tin chi tiết của một bài giảng
    @GetMapping("/{lessonId}")
    public ResponseEntity<CourseLessonResponseDTO> getLessonDetail(@PathVariable Long courseId,
                                                                   @PathVariable Long lessonId) {
        Optional<CourseLessonResponseDTO> lessonOpt = courseLessonService.getLessonDetail(courseId, lessonId);
        return lessonOpt.map(lesson -> new ResponseEntity<>(lesson, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Tạo mới bài giảng (Admin/Instructor)
    @PostMapping
    public ResponseEntity<CourseLessonResponseDTO> createLesson(@PathVariable Long courseId,
                                                                @RequestBody @Validated CourseLessonRequestDTO lessonDTO) {
        CourseLessonResponseDTO newLesson = courseLessonService.createLesson(courseId, lessonDTO);
        return new ResponseEntity<>(newLesson, HttpStatus.CREATED);
    }

    // Cập nhật bài giảng (Admin/Instructor)
    @PutMapping("/{lessonId}")
    public ResponseEntity<CourseLessonResponseDTO> updateLesson(@PathVariable Long courseId,
                                                                @PathVariable Long lessonId,
                                                                @RequestBody @Validated CourseLessonRequestDTO lessonDTO) {
        CourseLessonResponseDTO updatedLesson = courseLessonService.updateLesson(courseId, lessonId, lessonDTO);
        return new ResponseEntity<>(updatedLesson, HttpStatus.OK);
    }

    // Xóa bài giảng (Admin/Instructor)
    @DeleteMapping("/{lessonId}")
    public ResponseEntity<Void> deleteLesson(@PathVariable Long courseId, @PathVariable Long lessonId) {
        boolean deleted = courseLessonService.deleteLesson(courseId, lessonId);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
