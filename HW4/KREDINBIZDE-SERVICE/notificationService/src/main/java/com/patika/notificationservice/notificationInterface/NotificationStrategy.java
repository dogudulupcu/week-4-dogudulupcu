package com.patika.notificationservice.notificationInterface;

import com.patika.notificationservice.listener.dto.NotificationDTO;
import com.patika.notificationservice.listener.enums.NotificationType;

public interface NotificationStrategy {
    void sendNotification(NotificationDTO notificationDTO);

    boolean supports(NotificationType type);
}
