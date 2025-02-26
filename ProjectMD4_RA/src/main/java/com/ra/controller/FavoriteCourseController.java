package com.ra.controller;

import com.ra.model.dto.favoritecourse.FavoriteCourseRequestDTO;
import com.ra.model.dto.favoritecourse.FavoriteCourseResponseDTO;
import com.ra.service.favoritecourse.FavoriteCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/favorites")
public class FavoriteCourseController {

    @Autowired
    private FavoriteCourseService favoriteCourseService;

    // Thêm khóa học vào danh sách yêu thích
    @PostMapping
    public ResponseEntity<FavoriteCourseResponseDTO> addFavorite(@RequestBody FavoriteCourseRequestDTO request) {
        FavoriteCourseResponseDTO favorite = favoriteCourseService.addFavorite(request.getUserId(), request.getCourseId());
        return new ResponseEntity<>(favorite, HttpStatus.CREATED);
    }

    // Xóa khóa học khỏi danh sách yêu thích theo ID
    @DeleteMapping("/{favoriteId}")
    public ResponseEntity<String> removeFavorite(@PathVariable Long favoriteId) {
        boolean success = favoriteCourseService.removeFavoriteById(favoriteId);
        if (success) {
            return ResponseEntity.ok("Favorite removed successfully");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Favorite not found");
    }

    // Lấy danh sách khóa học yêu thích của user
    @GetMapping
    public ResponseEntity<List<FavoriteCourseResponseDTO>> getFavoriteCourses(@RequestParam Long userId) {
        List<FavoriteCourseResponseDTO> favorites = favoriteCourseService.getFavoriteCourses(userId);
        return new ResponseEntity<>(favorites, HttpStatus.OK);
    }
}
