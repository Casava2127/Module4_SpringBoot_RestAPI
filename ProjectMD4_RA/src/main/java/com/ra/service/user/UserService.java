package com.ra.service.user;

import com.ra.model.dto.user.ChangePasswordDTO;
import com.ra.model.dto.user.UserRequestDTO;
import com.ra.model.dto.user.UserResponseDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserResponseDTO> findAll();
    Optional<UserResponseDTO> findById(Long id);
    UserResponseDTO save(UserRequestDTO userDTO);
    UserResponseDTO update(Long id, UserRequestDTO userDTO);
    boolean delete(Long id);
    boolean changePassword(Long userId, ChangePasswordDTO changePasswordDTO);


}
