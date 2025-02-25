package com.ra.controller;

import com.ra.model.dto.notification.MarkNotificationRequest;
import com.ra.model.dto.notification.NotificationResponseDTO;
import com.ra.service.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/notifications")
@Validated
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public ResponseEntity<List<NotificationResponseDTO>> getUserNotifications(
            @RequestParam(required = false) Long userId) {
        List<NotificationResponseDTO> notifications;
        if (userId != null) {
            notifications = notificationService.getUserNotifications(userId);
        } else {
            notifications = notificationService.findAll(); // Trả về tất cả thông báo
        }
        return ResponseEntity.ok(notifications);
    }


    // Đánh dấu thông báo đã đọc
//    @PutMapping("/{notificationId}/read")
//    public ResponseEntity<String> markNotificationAsRead(
//            @RequestParam Long userId,
//            @PathVariable Long notificationId) {
//        boolean success = notificationService.markNotificationAsRead(userId, notificationId);
//        if (success) {
//            return ResponseEntity.ok("Notification marked as read");
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Notification not found or does not belong to user");
//        }
//    }
    // Đánh dấu thông báo đã đọc
    @PutMapping("/{notificationId}/read")
    public ResponseEntity<String> markNotificationAsRead(
            @PathVariable Long notificationId,
            @RequestBody MarkNotificationRequest request) {

        boolean success = notificationService.markNotificationAsRead(request.getUserId(), notificationId);
        if (success) {
            return ResponseEntity.ok("Notification marked as read");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Notification not found or does not belong to user");
        }
    }

}
