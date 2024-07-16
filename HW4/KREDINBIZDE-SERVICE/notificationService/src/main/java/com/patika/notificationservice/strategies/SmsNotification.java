package com.patika.notificationservice.strategies;

import com.patika.notificationservice.listener.dto.NotificationDTO;
import com.patika.notificationservice.listener.enums.NotificationType;
import com.patika.notificationservice.notificationInterface.NotificationStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SmsNotification implements NotificationStrategy {

    @Override
    public void sendNotification(NotificationDTO notificationDTO) {
        log.info("Sms gönderildi: {}", notificationDTO);
    }

    @Override
    public boolean supports(NotificationType type) {
        return type == NotificationType.SMS;
    }
}
