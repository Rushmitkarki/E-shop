package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.NotificationDto;
import com.ecommerce.ecommerce.entity.Category;
import com.ecommerce.ecommerce.entity.Notification;
import com.ecommerce.ecommerce.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class NotificationServiceImpl implements NotificationService {
    @Override
    public void addNotification(NotificationDto notificationDto) {

    }

    @Override
    public List<Notification> getData() {
        return null;
    }

    @Override
    public Category getByIdNoOpt(Integer id) {
        return null;
    }
}
