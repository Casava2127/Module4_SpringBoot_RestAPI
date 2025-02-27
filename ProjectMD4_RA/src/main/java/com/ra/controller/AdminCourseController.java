package com.ra.controller;

import com.ra.model.dto.course.CourseResponseDTO;
import com.ra.model.dto.user.UserResponseDTO;
import com.ra.model.dto.user.UserStatusUpdateDTO;
import com.ra.service.course.CourseService;
import com.ra.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/v1/admin/courses")
public class AdminCourseController  {
    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<Page<CourseResponseDTO>> getAllCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "courseName") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return ResponseEntity.ok(courseService.findAll(pageable));
    }


}
