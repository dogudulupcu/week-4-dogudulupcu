package com.patika.notificationservice.listener.dto;

import com.patika.notificationservice.listener.enums.NotificationType;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class NotificationDTO {

    private String message;
    private NotificationType notificationType;
    private String userEmail;
}
