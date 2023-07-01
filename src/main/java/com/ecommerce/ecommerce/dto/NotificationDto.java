package com.ecommerce.ecommerce.dto;

import com.ecommerce.ecommerce.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class NotificationDto{
    private int notifyId;
    private User user;
    private String notifyContent;
}
