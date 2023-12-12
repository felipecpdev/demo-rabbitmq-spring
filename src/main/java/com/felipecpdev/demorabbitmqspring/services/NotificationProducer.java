package com.felipecpdev.demorabbitmqspring.services;

import com.felipecpdev.demorabbitmqspring.config.RabbitMQProducer;
import com.felipecpdev.demorabbitmqspring.dto.NotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationProducer {

    @Value("${rabbitmq.exchanges.internal}")
    private String exchangeInternal;

    @Value("${rabbitmq.routing-keys.internal-notification}")
    private String routingKeyInternal;

    private final RabbitMQProducer rabbitMQProducer;
    private final Random random = new Random();

    public void pushNotification() {
        NotificationRequest notificationRequest = new NotificationRequest(
                random.nextInt(Integer.MAX_VALUE) + 1,
                random.nextInt(Integer.MAX_VALUE) + 1,
                "felipe.contreras.dev@gmail.com",
                "Hi AMQ, welocome to felipecpdev"
        );
        rabbitMQProducer.send(notificationRequest, exchangeInternal, routingKeyInternal);
    }


}
