package com.ecommerce.ecommerce.repo;

import com.ecommerce.ecommerce.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepo extends JpaRepository<Notification,Integer> {
    @Query(value = "select * from notify where user_id=?1",nativeQuery = true)
    List<Notification> getByUserId(int userId);
}
