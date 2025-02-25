package com.ra.service.course;

import com.ra.model.dto.course.CourseRequestDTO;
import com.ra.model.dto.course.CourseResponseDTO;
import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<CourseResponseDTO> findAll();
    Optional<CourseResponseDTO> findById(Long id);
    CourseResponseDTO save(CourseRequestDTO courseDTO);
    CourseResponseDTO update(Long id, CourseRequestDTO courseDTO);
    boolean delete(Long id);

    // Các chức năng mở rộng:
    List<CourseResponseDTO> searchCourses(String keyword);
    List<CourseResponseDTO> getFeaturedCourses();
    List<CourseResponseDTO> getNewCourses();
    List<CourseResponseDTO> getPopularCourses();
}
