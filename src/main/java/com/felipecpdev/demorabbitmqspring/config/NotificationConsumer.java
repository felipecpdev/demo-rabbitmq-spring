package com.felipecpdev.demorabbitmqspring.config;


import com.felipecpdev.demorabbitmqspring.dto.NotificationRequest;
import com.felipecpdev.demorabbitmqspring.models.Notification;
import com.felipecpdev.demorabbitmqspring.repositories.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository notificationRepository;

    @RabbitListener(queues = {"${rabbitmq.queues.notification}", "${rabbitmq.queues.notification-one}"})
    public void consumeNotification(NotificationRequest notificationRequest) {
        log.info("Message Queue ... {}", notificationRequest);

        notificationRepository.save(
                Notification.builder()
                        .notificationId(notificationRequest.getNotificationId())
                        .toCustomerId(notificationRequest.getToCustomerId())
                        .toCustomerEmail(notificationRequest.getToCustomerEmail())
                        .sender("felipecpdev")
                        .message(notificationRequest.getMessage())
                        .sendAt(LocalDateTime.now())
                        .build());
    }
}
