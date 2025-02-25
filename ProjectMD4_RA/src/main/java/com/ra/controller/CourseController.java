package com.ra.controller;

import com.ra.model.dto.course.CourseRequestDTO;
import com.ra.model.dto.course.CourseResponseDTO;
import com.ra.service.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // Lấy danh sách tất cả khóa học
    @GetMapping
    public ResponseEntity<List<CourseResponseDTO>> getAllCourses() {
        List<CourseResponseDTO> courses = courseService.findAll();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    // Tìm kiếm khóa học theo từ khóa
    @GetMapping("/search")
    public ResponseEntity<List<CourseResponseDTO>> searchCourses(@RequestParam String keyword) {
        List<CourseResponseDTO> courses = courseService.searchCourses(keyword);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    // Lấy danh sách khóa học nổi bật
    @GetMapping("/featured")
    public ResponseEntity<List<CourseResponseDTO>> getFeaturedCourses() {
        List<CourseResponseDTO> courses = courseService.getFeaturedCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    // Lấy danh sách khóa học mới
    @GetMapping("/new")
    public ResponseEntity<List<CourseResponseDTO>> getNewCourses() {
        List<CourseResponseDTO> courses = courseService.getNewCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    // Lấy danh sách khóa học phổ biến
    @GetMapping("/popular")
    public ResponseEntity<List<CourseResponseDTO>> getPopularCourses() {
        List<CourseResponseDTO> courses = courseService.getPopularCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    // Lấy thông tin chi tiết một khóa học theo courseId
    @GetMapping("/{courseId}")
    public ResponseEntity<CourseResponseDTO> getCourseById(@PathVariable Long courseId) {
        Optional<CourseResponseDTO> courseOpt = courseService.findById(courseId);
        return courseOpt.map(course -> new ResponseEntity<>(course, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Tạo mới khóa học
    @PostMapping("/create")
    public ResponseEntity<CourseResponseDTO> createCourse(@RequestBody CourseRequestDTO courseDTO) {
        CourseResponseDTO newCourse = courseService.save(courseDTO);
        return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
    }

    // Cập nhật khóa học
    @PutMapping("/update/{courseId}")
    public ResponseEntity<CourseResponseDTO> updateCourse(@PathVariable Long courseId, @RequestBody CourseRequestDTO courseDTO) {
        CourseResponseDTO updatedCourse = courseService.update(courseId, courseDTO);
        return updatedCourse != null
                ? new ResponseEntity<>(updatedCourse, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Xóa khóa học
    @DeleteMapping("/delete/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long courseId) {
        boolean deleted = courseService.delete(courseId);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
