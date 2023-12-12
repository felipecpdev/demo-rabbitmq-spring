package com.felipecpdev.demorabbitmqspring.controllers;

import com.felipecpdev.demorabbitmqspring.services.NotificationProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notification")
public class NotificationController {

    private final NotificationProducer notificationProducer;

    @GetMapping("/notification-push")
    public ResponseEntity<Object> notificationPush() {
        notificationProducer.pushNotification();
        return ResponseEntity.ok("notification-push-sended");
    }
}
