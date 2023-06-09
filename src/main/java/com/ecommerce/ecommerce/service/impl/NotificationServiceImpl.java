package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.NotificationDto;
import com.ecommerce.ecommerce.entity.Category;
import com.ecommerce.ecommerce.entity.Notification;
import com.ecommerce.ecommerce.repo.NotificationRepo;
import com.ecommerce.ecommerce.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepo notificationRepo;

    @Override
    public void addNotification(NotificationDto notificationDto) {
        Notification notification = new Notification();
        notification.setNotifyId(notificationDto.getNotifyId());
        notification.setUser(notificationDto.getUser());
        notification.setNotifyContent(notificationDto.getNotifyContent());
        notificationRepo.save(notification);
    }

    @Override
    public List<Notification> getData() {

        return notificationRepo.findAll();
    }

    @Override
    public Notification getByIdNoOpt(Integer id) {

        return notificationRepo.findById(id).orElse(null);
    }
}
