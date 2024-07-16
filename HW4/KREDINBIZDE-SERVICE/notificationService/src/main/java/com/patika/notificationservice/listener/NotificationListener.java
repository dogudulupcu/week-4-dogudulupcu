package com.patika.notificationservice.listener;


import com.patika.notificationservice.listener.dto.NotificationDTO;
import com.patika.notificationservice.notificationService.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationListener {

    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void sendNotification(NotificationDTO notificationDTO){
        log.info("Kuyruktan okundu: {}",notificationDTO);
        notificationService.sendNotification(notificationDTO);

    }

}
