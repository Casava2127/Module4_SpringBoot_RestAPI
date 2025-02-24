package com.ra.service.notification;

import com.ra.model.dto.notification.NotificationRequestDTO;
import com.ra.model.dto.notification.NotificationResponseDTO;

import java.util.List;
import java.util.Optional;

public interface NotificationService {
    List<NotificationResponseDTO> findAll();
    Optional<NotificationResponseDTO> findById(Long id);
    NotificationResponseDTO save(NotificationRequestDTO notificationDTO);
    NotificationResponseDTO update(Long id, NotificationRequestDTO notificationDTO);
    boolean delete(Long id);
}
