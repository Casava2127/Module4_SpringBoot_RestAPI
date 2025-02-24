package com.ra.model.dto.notification;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationRequestDTO {
    private Long userId;
    private String title;
    private String message;
    private boolean isRead;
}
