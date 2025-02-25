package com.ra.controller;

import com.ra.model.dto.favoritecourse.FavoriteCourseResponseDTO;
import com.ra.service.favoritecourse.FavoriteCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/favorites")
public class FavoriteCourseController {

    @Autowired
    private FavoriteCourseService favoriteCourseService;

    // Thêm khóa học vào danh sách yêu thích của user
    @PostMapping("/add")
    public ResponseEntity<FavoriteCourseResponseDTO> addFavorite(
            @RequestParam Long userId,
            @RequestParam Long courseId) {
        FavoriteCourseResponseDTO favorite = favoriteCourseService.addFavorite(userId, courseId);
        return new ResponseEntity<>(favorite, HttpStatus.CREATED);
    }

    // Xóa khóa học khỏi danh sách yêu thích của user
    @DeleteMapping("/remove")
    public ResponseEntity<String> removeFavorite(
            @RequestParam Long userId,
            @RequestParam Long courseId) {
        boolean success = favoriteCourseService.removeFavorite(userId, courseId);
        if (success) {
            return ResponseEntity.ok("Favorite removed successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Favorite not found");
    }

    // Lấy danh sách khóa học yêu thích của user
    @GetMapping
    public ResponseEntity<List<FavoriteCourseResponseDTO>> getFavoriteCourses(@RequestParam Long userId) {
        List<FavoriteCourseResponseDTO> favorites = favoriteCourseService.getFavoriteCourses(userId);
        return new ResponseEntity<>(favorites, HttpStatus.OK);
    }
}
