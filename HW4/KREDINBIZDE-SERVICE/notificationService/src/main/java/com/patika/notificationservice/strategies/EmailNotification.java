package com.patika.notificationservice.strategies;

import com.patika.notificationservice.listener.dto.NotificationDTO;
import com.patika.notificationservice.listener.enums.NotificationType;
import com.patika.notificationservice.notificationInterface.NotificationStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
@Slf4j
public class EmailNotification implements NotificationStrategy {

    @Override
    public void sendNotification(NotificationDTO notificationDTO) {
        log.info("Email gönderildi: {}", notificationDTO);
    }

    @Override
    public boolean supports(NotificationType type) {
        return type == NotificationType.EMAIL;
    }
}
