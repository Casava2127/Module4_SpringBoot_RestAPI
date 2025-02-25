package com.ra.service.course;



import com.ra.model.dto.CourseCartRequestDTO;
import com.ra.model.dto.CourseCartResponseDTO;

import java.util.List;

public interface CourseCartService {
    List<CourseCartResponseDTO> getCartItems(Long userId);
    void addToCart(Long userId, CourseCartRequestDTO requestDTO);
    void updateCartItem(Long userId, Long cartId, int quantity);
    void removeCartItem(Long userId, Long cartId);
    void clearCart(Long userId);
}
