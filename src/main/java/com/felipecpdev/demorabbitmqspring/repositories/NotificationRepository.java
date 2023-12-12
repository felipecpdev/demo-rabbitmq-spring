package com.felipecpdev.demorabbitmqspring.repositories;

import com.felipecpdev.demorabbitmqspring.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}
