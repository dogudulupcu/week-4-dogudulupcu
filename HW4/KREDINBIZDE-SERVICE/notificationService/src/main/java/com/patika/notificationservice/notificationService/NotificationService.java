package com.patika.notificationservice.notificationService;


import com.patika.notificationservice.listener.dto.NotificationDTO;
import com.patika.notificationservice.listener.enums.NotificationType;
import com.patika.notificationservice.notificationInterface.NotificationStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class NotificationService {

    private final List<NotificationStrategy> strategies;

    public NotificationService(List<NotificationStrategy> strategies) {
        this.strategies = strategies;
    }

    public void sendNotification(NotificationDTO notificationDTO) {
        NotificationType type = notificationDTO.getNotificationType();
        for (NotificationStrategy strategy : strategies) {
            if (strategy.supports(type)) {
                strategy.sendNotification(notificationDTO);
                return;
            }
        }
        log.error("No strategy found for notification type: {}", type);
    }
}
