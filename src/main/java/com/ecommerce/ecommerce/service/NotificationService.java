package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.NotificationDto;
import com.ecommerce.ecommerce.entity.Category;
import com.ecommerce.ecommerce.entity.Notification;

import java.util.List;

public interface NotificationService {
    void addNotification();

    List<Notification> getData();

    Notification getByIdNoOpt(Integer id);

    List<Notification> getNotification();
}
