package com.ra.service.courseLesson.imp;

import com.ra.model.dto.courseLesson.CourseLessonRequestDTO;
import com.ra.model.dto.courseLesson.CourseLessonResponseDTO;
import com.ra.model.entity.Course;
import com.ra.model.entity.CourseLesson;
import com.ra.repository.CourseLessonRepository;
import com.ra.repository.CourseRepository;
import com.ra.service.courseLesson.CourseLessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseLessonServiceImp implements CourseLessonService {
    @Autowired
    private CourseLessonRepository courseLessonRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<CourseLessonResponseDTO> findAll() {
        return courseLessonRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CourseLessonResponseDTO> findById(Long id) {
        return courseLessonRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public CourseLessonResponseDTO save(CourseLessonRequestDTO requestDTO) {
        Course course = courseRepository.findById(requestDTO.getCourseId()).orElseThrow(
                () -> new RuntimeException("Course not found"));

        CourseLesson courseLesson = CourseLesson.builder()
                .course(course)
                .lessonTitle(requestDTO.getLessonTitle())
                .content(requestDTO.getContent())
                .videoUrl(requestDTO.getVideoUrl())
                .sortOrder(requestDTO.getSortOrder())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return convertToDTO(courseLessonRepository.save(courseLesson));
    }

    @Override
    public CourseLessonResponseDTO update(Long id, CourseLessonRequestDTO requestDTO) {
        CourseLesson courseLesson = courseLessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        courseLesson.setLessonTitle(requestDTO.getLessonTitle());
        courseLesson.setContent(requestDTO.getContent());
        courseLesson.setVideoUrl(requestDTO.getVideoUrl());
        courseLesson.setSortOrder(requestDTO.getSortOrder());
        courseLesson.setUpdatedAt(LocalDateTime.now());

        return convertToDTO(courseLessonRepository.save(courseLesson));
    }

    @Override
    public boolean delete(Long id) {
        if (!courseLessonRepository.existsById(id)) return false;
        courseLessonRepository.deleteById(id);
        return true;
    }

    private CourseLessonResponseDTO convertToDTO(CourseLesson lesson) {
        return CourseLessonResponseDTO.builder()
                .lessonId(lesson.getLessonId())
                .lessonTitle(lesson.getLessonTitle())
                .content(lesson.getContent())
                .videoUrl(lesson.getVideoUrl())
                .sortOrder(lesson.getSortOrder())
                .createdAt(lesson.getCreatedAt())
                .updatedAt(lesson.getUpdatedAt())
                .build();
    }
}
