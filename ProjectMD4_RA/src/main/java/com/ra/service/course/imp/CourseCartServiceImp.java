package com.ra.service.course.imp;

import com.ra.model.dto.CourseCartRequestDTO;
import com.ra.model.dto.CourseCartResponseDTO;
import com.ra.exception.ResourceNotFoundException;
import com.ra.model.entity.Course;
import com.ra.model.entity.CourseCart;
import com.ra.model.entity.User;
import com.ra.repository.CourseCartRepository;
import com.ra.repository.CourseRepository;
import com.ra.repository.UserRepository;
import com.ra.service.course.CourseCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseCartServiceImp implements CourseCartService {

    private final CourseCartRepository courseCartRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Override
    public List<CourseCartResponseDTO> getCartItems(Long userId) {
        return courseCartRepository.findByUserUserId(userId)
                .stream()
                .map(cart -> CourseCartResponseDTO.builder()
                        .cartId(cart.getCartId())
                        .courseId(cart.getCourse().getCourseId())
                        .courseName(cart.getCourse().getCourseName())
                        .quantity(cart.getQuantity())
                        .courseImage(cart.getCourse().getImage())
                        .price(cart.getCourse().getPrice().doubleValue())  // Chuyển BigDecimal -> double
                        .build())
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public void addToCart(Long userId, CourseCartRequestDTO requestDTO) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }
        if (requestDTO.getCourseId() == null) {
            throw new IllegalArgumentException("Course ID must not be null");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Course course = courseRepository.findById(requestDTO.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        CourseCart cartItem = courseCartRepository.findByUserUserIdAndCourseCourseId(userId, requestDTO.getCourseId())
                .orElse(null);

        if (cartItem == null) {
            cartItem = CourseCart.builder()
                    .user(user)
                    .course(course)
                    .quantity(requestDTO.getQuantity())
                    .createdAt(LocalDateTime.now())
                    .build();
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + requestDTO.getQuantity());
        }

        courseCartRepository.save(cartItem);
    }


    @Override
    @Transactional
    public void updateCartItem(Long userId, Long cartId, int quantity) {
        CourseCart cartItem = courseCartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found"));

        if (!cartItem.getUser().getUserId().equals(userId)) {
            throw new IllegalStateException("Unauthorized action");
        }

        cartItem.setQuantity(quantity);
        courseCartRepository.save(cartItem);
    }

    @Override
    @Transactional
    public void removeCartItem(Long userId, Long cartId) {
        CourseCart cartItem = courseCartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found"));

        if (!cartItem.getUser().getUserId().equals(userId)) {
            throw new IllegalStateException("Unauthorized action");
        }

        courseCartRepository.delete(cartItem);
    }

    @Override
    @Transactional
    public void clearCart(Long userId) {
        courseCartRepository.deleteByUserUserId(userId);
    }
}
