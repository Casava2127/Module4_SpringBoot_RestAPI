package com.ra.service.course.imp;

import com.ra.model.dto.course.CourseRequestDTO;
import com.ra.model.dto.course.CourseResponseDTO;
import com.ra.model.entity.Course;
import com.ra.repository.CourseRepository;
import com.ra.repository.CategoryRepository;
import com.ra.repository.UserRepository;
import com.ra.service.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        List<Course> courses = courseRepository.findAll();
        System.out.println("Danh sách khóa học: " + courses);
        return courses.stream()
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

        // Kiểm tra Category có tồn tại không trước khi cập nhật
        if (courseDTO.getCategoryId() != null) {
            categoryRepository.findById(courseDTO.getCategoryId())
                    .ifPresentOrElse(existingCourse::setCategory,
                            () -> {
                                throw new RuntimeException("Category with ID " + courseDTO.getCategoryId() + " not found");
                            });
        }

        // Kiểm tra Instructor có tồn tại không trước khi cập nhật
        if (courseDTO.getInstructorId() != null) {
            userRepository.findById(courseDTO.getInstructorId())
                    .ifPresentOrElse(existingCourse::setInstructor,
                            () -> {
                                throw new RuntimeException("Instructor with ID " + courseDTO.getInstructorId() + " not found");
                            });
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

    // Dummy implementation: Sử dụng các tiêu chí giả định để lọc danh sách
    @Override
    public List<CourseResponseDTO> searchCourses(String keyword) {
        return courseRepository.findAll().stream()
                .filter(course -> course.getCourseName().toLowerCase().contains(keyword.toLowerCase())
                        || (course.getDescription() != null && course.getDescription().toLowerCase().contains(keyword.toLowerCase())))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseResponseDTO> getFeaturedCourses() {
        // Giả sử khóa học có availableSlots < 20 là "nổi bật"
        return courseRepository.findAll().stream()
                .filter(course -> course.getAvailableSlots() < 20)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseResponseDTO> getNewCourses() {
        // Giả sử khóa học được tạo trong vòng 7 ngày là "mới"
        return courseRepository.findAll().stream()
                .filter(course -> course.getCreatedAt().isAfter(java.time.LocalDateTime.now().minusDays(7)))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseResponseDTO> getPopularCourses() {
        // Giả sử khóa học có availableSlots < 5 là "phổ biến" (vì số lượng đăng ký cao)
        return courseRepository.findAll().stream()
                .filter(course -> course.getAvailableSlots() < 5)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    @Override
    public Page<CourseResponseDTO> findAll(Pageable pageable) {
        return courseRepository.findAll(pageable).map(this::convertToDTO);
    }

    //@Override
//    public CourseResponseDTO update(Long id, CourseRequestDTO courseDTO) {
//        Course course = courseRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
//
//        if (courseDTO.getCategoryId() != null) {
//            Category category = categoryRepository.findById(courseDTO.getCategoryId())
//                    .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
//            course.setCategory(category);
//        }
//
//        if (courseDTO.getInstructorId() != null) {
//            User instructor = userRepository.findById(courseDTO.getInstructorId())
//                    .orElseThrow(() -> new ResourceNotFoundException("Instructor not found"));
//            course.setInstructor(instructor);
//        }
//
//        course.setCourseName(courseDTO.getCourseName());
//        course.setPrice(courseDTO.getPrice());
//        course.setAvailableSlots(courseDTO.getAvailableSlots());
//        course.setImage(courseDTO.getImage());
//        course.setDescription(courseDTO.getDescription());
//
//        return convertToDTO(courseRepository.save(course));
//    }


    // Các hàm chuyển đổi giữa Entity và DTO
    private CourseResponseDTO convertToDTO(Course course) {
        return CourseResponseDTO.builder()
                .courseId(course.getCourseId())
                .sku(course.getSku())
                .courseName(course.getCourseName())
                .price(course.getPrice())
                .availableSlots(course.getAvailableSlots())
                .image(course.getImage())
                .description(course.getDescription())
                .categoryId(course.getCategory() != null ? course.getCategory().getCategoryId() : null)
                .categoryName(course.getCategory() != null ? course.getCategory().getCategoryName() : null)
                .instructorId(course.getInstructor() != null ? course.getInstructor().getUserId() : null)
                .instructorName(course.getInstructor() != null ? course.getInstructor().getFullname() : null)
                .createdAt(course.getCreatedAt())
                .updatedAt(course.getUpdatedAt())
                .build();
    }

    private Course convertToEntity(CourseRequestDTO courseDTO) {
        Course course = new Course();
        course.setCourseName(courseDTO.getCourseName());
        course.setPrice(courseDTO.getPrice());
        course.setImage(courseDTO.getImage());
        course.setAvailableSlots(courseDTO.getAvailableSlots());
        course.setDescription(courseDTO.getDescription());

        // Kiểm tra Category có tồn tại không trước khi gán
        if (courseDTO.getCategoryId() != null) {
            categoryRepository.findById(courseDTO.getCategoryId())
                    .ifPresentOrElse(course::setCategory,
                            () -> {
                                throw new RuntimeException("Category with ID " + courseDTO.getCategoryId() + " not found");
                            });
        }

        // Kiểm tra Instructor có tồn tại không trước khi gán
        if (courseDTO.getInstructorId() != null) {
            userRepository.findById(courseDTO.getInstructorId())
                    .ifPresentOrElse(course::setInstructor,
                            () -> {
                                throw new RuntimeException("Instructor with ID " + courseDTO.getInstructorId() + " not found");
                            });
        }

        return course;
    }

}
