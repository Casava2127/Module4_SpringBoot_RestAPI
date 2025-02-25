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

    @Override
    public List<NotificationResponseDTO> getUserNotifications(Long userId) {
        List<Notification> notifications = notificationRepository.findByUserUserId(userId);
        return notifications.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean markNotificationAsRead(Long userId, Long notificationId) {
        Optional<Notification> notificationOpt = notificationRepository.findById(notificationId);
        if (notificationOpt.isEmpty()) {
            return false;
        }

        Notification notification = notificationOpt.get();

        // Kiểm tra nếu thông báo không có user hoặc không thuộc về userId, trả về false
        if (notification.getUser() == null || !notification.getUser().getUserId().equals(userId)) {
            return false;
        }

        notification.setRead(true);
        notificationRepository.save(notification);
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
        Optional<User> userOpt = userRepository.findById(notificationDTO.getUserId());

        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("User with ID " + notificationDTO.getUserId() + " not found.");
        }

        return Notification.builder()
                .user(userOpt.get())
                .title(notificationDTO.getTitle())
                .message(notificationDTO.getMessage())
                .isRead(notificationDTO.isRead())
                .createdAt(LocalDateTime.now())
                .build();
    }

}
