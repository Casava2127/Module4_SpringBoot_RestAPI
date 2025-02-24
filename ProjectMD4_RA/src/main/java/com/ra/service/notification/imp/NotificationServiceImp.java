package com.ra.service.notification.imp;

import com.ra.model.dto.notification.NotificationRequestDTO;
import com.ra.model.dto.notification.NotificationResponseDTO;
import com.ra.model.entity.Notification;
import com.ra.model.entity.User;
import com.ra.repository.NotificationRepository;
import com.ra.repository.UserRepository;
import com.ra.service.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImp implements NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<NotificationResponseDTO> findAll() {
        return notificationRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<NotificationResponseDTO> findById(Long id) {
        return notificationRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public NotificationResponseDTO save(NotificationRequestDTO notificationDTO) {
        Notification notification = convertToEntity(notificationDTO);
        Notification savedNotification = notificationRepository.save(notification);
        return convertToDTO(savedNotification);
    }

    @Override
    public NotificationResponseDTO update(Long id, NotificationRequestDTO notificationDTO) {
        Optional<Notification> optionalNotification = notificationRepository.findById(id);
        if (!optionalNotification.isPresent()) return null;

        Notification notification = optionalNotification.get();
        notification.setTitle(notificationDTO.getTitle());
        notification.setMessage(notificationDTO.getMessage());
        notification.setRead(notificationDTO.isRead());

        Notification updatedNotification = notificationRepository.save(notification);
        return convertToDTO(updatedNotification);
    }

    @Override
    public boolean delete(Long id) {
        if (!notificationRepository.existsById(id)) return false;
        notificationRepository.deleteById(id);
        return true;
    }

    private NotificationResponseDTO convertToDTO(Notification notification) {
        return NotificationResponseDTO.builder()
                .notificationId(notification.getNotificationId())
                .userName(notification.getUser().getUsername())
                .title(notification.getTitle())
                .message(notification.getMessage())
                .isRead(notification.isRead())
                .createdAt(notification.getCreatedAt())
                .build();
    }

    private Notification convertToEntity(NotificationRequestDTO notificationDTO) {
        User user = userRepository.findById(notificationDTO.getUserId()).orElse(null);

        return Notification.builder()
                .user(user)
                .title(notificationDTO.getTitle())
                .message(notificationDTO.getMessage())
                .isRead(notificationDTO.isRead())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
