//package com.ra.service.courseLesson.imp;
//
//import com.ra.model.dto.courseLesson.CourseLessonRequestDTO;
//import com.ra.model.dto.courseLesson.CourseLessonResponseDTO;
//import com.ra.model.entity.Course;
//import com.ra.model.entity.CourseLesson;
//import com.ra.repository.CourseLessonRepository;
//import com.ra.repository.CourseRepository;
//import com.ra.service.courseLesson.CourseLessonService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class CourseLessonServiceImp implements CourseLessonService {
//    @Autowired
//    private CourseLessonRepository courseLessonRepository;
//
//    @Autowired
//    private CourseRepository courseRepository;
//
//    @Override
//    public List<CourseLessonResponseDTO> findAll() {
//        return courseLessonRepository.findAll().stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public Optional<CourseLessonResponseDTO> findById(Long id) {
//        return courseLessonRepository.findById(id).map(this::convertToDTO);
//    }
//
//    @Override
//    public CourseLessonResponseDTO save(CourseLessonRequestDTO requestDTO) {
//        Course course = courseRepository.findById(requestDTO.getCourseId()).orElseThrow(
//                () -> new RuntimeException("Course not found"));
//
//        CourseLesson courseLesson = CourseLesson.builder()
//                .course(course)
//                .lessonTitle(requestDTO.getLessonTitle())
//                .content(requestDTO.getContent())
//                .videoUrl(requestDTO.getVideoUrl())
//                .sortOrder(requestDTO.getSortOrder())
//                .createdAt(LocalDateTime.now())
//                .updatedAt(LocalDateTime.now())
//                .build();
//
//        return convertToDTO(courseLessonRepository.save(courseLesson));
//    }
//
//    @Override
//    public CourseLessonResponseDTO update(Long id, CourseLessonRequestDTO requestDTO) {
//        CourseLesson courseLesson = courseLessonRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Lesson not found"));
//
//        courseLesson.setLessonTitle(requestDTO.getLessonTitle());
//        courseLesson.setContent(requestDTO.getContent());
//        courseLesson.setVideoUrl(requestDTO.getVideoUrl());
//        courseLesson.setSortOrder(requestDTO.getSortOrder());
//        courseLesson.setUpdatedAt(LocalDateTime.now());
//
//        return convertToDTO(courseLessonRepository.save(courseLesson));
//    }
//
//    @Override
//    public boolean delete(Long id) {
//        if (!courseLessonRepository.existsById(id)) return false;
//        courseLessonRepository.deleteById(id);
//        return true;
//    }
//
//    private CourseLessonResponseDTO convertToDTO(CourseLesson lesson) {
//        return CourseLessonResponseDTO.builder()
//                .lessonId(lesson.getLessonId())
//                .lessonTitle(lesson.getLessonTitle())
//                .content(lesson.getContent())
//                .videoUrl(lesson.getVideoUrl())
//                .sortOrder(lesson.getSortOrder())
//                .createdAt(lesson.getCreatedAt())
//                .updatedAt(lesson.getUpdatedAt())
//                .build();
//    }
//}
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
    public List<CourseLessonResponseDTO> getLessonsByCourseId(Long courseId) {
        List<CourseLesson> lessons = courseLessonRepository.findByCourseCourseId(courseId);
        return lessons.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CourseLessonResponseDTO> getLessonDetail(Long courseId, Long lessonId) {
        // Kiểm tra xem bài giảng có thuộc về khóa học không
        Optional<CourseLesson> lessonOpt = courseLessonRepository.findById(lessonId);
        if (lessonOpt.isPresent() && lessonOpt.get().getCourse().getCourseId().equals(courseId)) {
            return Optional.of(convertToDTO(lessonOpt.get()));
        }
        return Optional.empty();
    }

    @Override
    public CourseLessonResponseDTO createLesson(Long courseId, CourseLessonRequestDTO lessonDTO) {
        // Kiểm tra khóa học tồn tại không
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        CourseLesson lesson = CourseLesson.builder()
                .course(course)
                .lessonTitle(lessonDTO.getLessonTitle())
                .content(lessonDTO.getContent())
                .videoUrl(lessonDTO.getVideoUrl())
                .sortOrder(lessonDTO.getSortOrder())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        CourseLesson savedLesson = courseLessonRepository.save(lesson);
        return convertToDTO(savedLesson);
    }

    @Override
    public CourseLessonResponseDTO updateLesson(Long courseId, Long lessonId, CourseLessonRequestDTO lessonDTO) {
        CourseLesson lesson = courseLessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        // Kiểm tra bài giảng có thuộc về khóa học hay không
        if (!lesson.getCourse().getCourseId().equals(courseId)) {
            throw new RuntimeException("Lesson does not belong to the specified course");
        }

        lesson.setLessonTitle(lessonDTO.getLessonTitle());
        lesson.setContent(lessonDTO.getContent());
        lesson.setVideoUrl(lessonDTO.getVideoUrl());
        lesson.setSortOrder(lessonDTO.getSortOrder());
        lesson.setUpdatedAt(LocalDateTime.now());

        CourseLesson updatedLesson = courseLessonRepository.save(lesson);
        return convertToDTO(updatedLesson);
    }

    @Override
    public boolean deleteLesson(Long courseId, Long lessonId) {
        Optional<CourseLesson> lessonOpt = courseLessonRepository.findById(lessonId);
        if (lessonOpt.isEmpty() || !lessonOpt.get().getCourse().getCourseId().equals(courseId)) {
            return false;
        }
        courseLessonRepository.deleteById(lessonId);
        return true;
    }

    private CourseLessonResponseDTO convertToDTO(CourseLesson lesson) {
        return CourseLessonResponseDTO.builder()
                .lessonId(lesson.getLessonId())
                .courseId(lesson.getCourse().getCourseId())
                .lessonTitle(lesson.getLessonTitle())
                .content(lesson.getContent())
                .videoUrl(lesson.getVideoUrl())
                .sortOrder(lesson.getSortOrder()!= null ? lesson.getSortOrder() :0)
                .updatedAt(lesson.getUpdatedAt())
                .build();
    }
}
