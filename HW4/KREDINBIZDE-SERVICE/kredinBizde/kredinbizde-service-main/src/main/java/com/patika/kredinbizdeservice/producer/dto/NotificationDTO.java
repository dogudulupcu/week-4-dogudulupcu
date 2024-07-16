package com.patika.kredinbizdeservice.producer.dto;

import com.patika.kredinbizdeservice.producer.enums.NotificationType;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class NotificationDTO {

    private String message;
    private NotificationType notificationType;
    private String userEmail;
}
