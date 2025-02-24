package com.ra.service.course.imp;

import com.ra.model.dto.course.CourseRequestDTO;
import com.ra.model.dto.course.CourseResponseDTO;
import com.ra.model.entity.Course;
import com.ra.model.entity.Category;
import com.ra.model.entity.User;
import com.ra.repository.CourseRepository;
import com.ra.repository.CategoryRepository;
import com.ra.repository.UserRepository;
import com.ra.service.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImp implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<CourseResponseDTO> findAll() {
        return courseRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CourseResponseDTO> findById(Long id) {
        return courseRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public CourseResponseDTO save(CourseRequestDTO courseDTO) {
        Course course = convertToEntity(courseDTO);
        Course savedCourse = courseRepository.save(course);
        return convertToDTO(savedCourse);
    }

    @Override
    public CourseResponseDTO update(Long id, CourseRequestDTO courseDTO) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (!optionalCourse.isPresent()) {
            return null;
        }

        Course existingCourse = optionalCourse.get();
        existingCourse.setCourseName(courseDTO.getCourseName());
        existingCourse.setPrice(courseDTO.getPrice());
        existingCourse.setImage(courseDTO.getImage());
        existingCourse.setAvailableSlots(courseDTO.getAvailableSlots());
        existingCourse.setDescription(courseDTO.getDescription());

        if (courseDTO.getCategoryId() != null) {
            categoryRepository.findById(courseDTO.getCategoryId()).ifPresent(existingCourse::setCategory);
        }

        if (courseDTO.getInstructorId() != null) {
            userRepository.findById(courseDTO.getInstructorId()).ifPresent(existingCourse::setInstructor);
        }

        Course updatedCourse = courseRepository.save(existingCourse);
        return convertToDTO(updatedCourse);
    }

    @Override
    public boolean delete(Long id) {
        if (!courseRepository.existsById(id)) {
            return false;
        }
        courseRepository.deleteById(id);
        return true;
    }

    private CourseResponseDTO convertToDTO(Course course) {
        return CourseResponseDTO.builder()
                .courseId(course.getCourseId())
                .courseName(course.getCourseName())
                .price(course.getPrice())
                .availableSlots(course.getAvailableSlots())
                .image(course.getImage())
                .description(course.getDescription())
                .categoryName(course.getCategory() != null ? course.getCategory().getCategoryName() : null)
                .instructorName(course.getInstructor() != null ? course.getInstructor().getFullname() : null)
                .build();
    }

    private Course convertToEntity(CourseRequestDTO courseDTO) {
        Course course = new Course();
        course.setCourseName(courseDTO.getCourseName());
        course.setPrice(courseDTO.getPrice());
        course.setImage(courseDTO.getImage());
        course.setAvailableSlots(courseDTO.getAvailableSlots());
        course.setDescription(courseDTO.getDescription());

        if (courseDTO.getCategoryId() != null) {
            categoryRepository.findById(courseDTO.getCategoryId()).ifPresent(course::setCategory);
        }

        if (courseDTO.getInstructorId() != null) {
            userRepository.findById(courseDTO.getInstructorId()).ifPresent(course::setInstructor);
        }

        return course;
    }
}
