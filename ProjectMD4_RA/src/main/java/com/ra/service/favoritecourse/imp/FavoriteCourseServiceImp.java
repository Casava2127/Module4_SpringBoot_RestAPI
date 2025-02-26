package com.ra.service.favoritecourse.imp;

import com.ra.model.dto.favoritecourse.FavoriteCourseResponseDTO;
import com.ra.model.entity.Course;
import com.ra.model.entity.FavoriteCourse;
import com.ra.model.entity.User;
import com.ra.repository.CourseRepository;
import com.ra.repository.FavoriteCourseRepository;
import com.ra.repository.UserRepository;
import com.ra.service.favoritecourse.FavoriteCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FavoriteCourseServiceImp implements FavoriteCourseService {

    @Autowired
    private FavoriteCourseRepository favoriteCourseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public FavoriteCourseResponseDTO addFavorite(Long userId, Long courseId) {
        // Kiểm tra user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));

        // Kiểm tra nếu đã có, trả về favorite hiện tại
        Optional<FavoriteCourse> existingFavorite = favoriteCourseRepository.findByUserUserIdAndCourseCourseId(userId, courseId);
        if(existingFavorite.isPresent()){
            return convertToDTO(existingFavorite.get());
        }

        // Tạo FavoriteCourse mới
        FavoriteCourse favorite = FavoriteCourse.builder()
                .user(user)
                .course(course)
                .createdAt(LocalDateTime.now())
                .build();

        FavoriteCourse savedFavorite = favoriteCourseRepository.save(favorite);
        return convertToDTO(savedFavorite);
    }

    @Override
    public boolean removeFavoriteById(Long favoriteId) {
        Optional<FavoriteCourse> favoriteOpt = favoriteCourseRepository.findById(favoriteId);
        if (favoriteOpt.isEmpty()) {
            return false;
        }
        favoriteCourseRepository.delete(favoriteOpt.get());
        return true;
    }

    @Override
    public List<FavoriteCourseResponseDTO> getFavoriteCourses(Long userId) {
        List<FavoriteCourse> favorites = favoriteCourseRepository.findByUserUserId(userId);
        return favorites.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private FavoriteCourseResponseDTO convertToDTO(FavoriteCourse favorite) {
        return FavoriteCourseResponseDTO.builder()
                .favoriteId(favorite.getFavoriteId())
                .courseId(favorite.getCourse().getCourseId())
                .courseName(favorite.getCourse().getCourseName())
                .addedAt(favorite.getCreatedAt())
                .build();
    }
}
