package com.ecommerce.ecommerce.repo;

import com.ecommerce.ecommerce.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepo extends JpaRepository<Notification,Integer> {
}
