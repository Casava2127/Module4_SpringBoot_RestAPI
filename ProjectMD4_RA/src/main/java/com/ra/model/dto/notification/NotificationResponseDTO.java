package com.ra.model.dto.notification;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationResponseDTO {
    private Long notificationId;
    private String userName;
    private String title;
    private String message;
    private boolean isRead;
    private LocalDateTime createdAt;
}
