package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.NotificationDto;
import com.ecommerce.ecommerce.entity.Category;
import com.ecommerce.ecommerce.entity.Notification;

import java.util.List;

public interface NotificationService {
    void addNotification(NotificationDto notificationDto);

    List<Notification> getData();

    Notification getByIdNoOpt(Integer id);
}
