package com.ra.service.courseLesson;

import com.ra.model.dto.courseLesson.CourseLessonRequestDTO;
import com.ra.model.dto.courseLesson.CourseLessonResponseDTO;

import java.util.List;
import java.util.Optional;

public interface CourseLessonService {
//    List<CourseLessonResponseDTO> findAll();
//    Optional<CourseLessonResponseDTO> findById(Long id);
//    CourseLessonResponseDTO save(CourseLessonRequestDTO dto);
//    CourseLessonResponseDTO update(Long id, CourseLessonRequestDTO dto);
//    boolean delete(Long id);
//
List<CourseLessonResponseDTO> getLessonsByCourseId(Long courseId);
    Optional<CourseLessonResponseDTO> getLessonDetail(Long courseId, Long lessonId);
    CourseLessonResponseDTO createLesson(Long courseId, CourseLessonRequestDTO lessonDTO);
    CourseLessonResponseDTO updateLesson(Long courseId, Long lessonId, CourseLessonRequestDTO lessonDTO);
    boolean deleteLesson(Long courseId, Long lessonId);

}
