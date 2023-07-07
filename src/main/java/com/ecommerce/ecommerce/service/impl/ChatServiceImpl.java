package com.ecommerce.ecommerce.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.dto.ChatDto;
import com.ecommerce.ecommerce.entity.Chat;
import com.ecommerce.ecommerce.repo.*;
import com.ecommerce.ecommerce.service.ChatService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepo chatRepo;
    private final UserRepo userRepo;

    @Override
    public void saveChat(ChatDto chatDto) {
        Chat chat = new Chat();
        chat.setChatText(chatDto.getChatText());
        chat.setUser1(userRepo.findById(chatDto.getUser1()).get());
        chat.setUser2(userRepo.findById(chatDto.getUser2()).get());

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        chat.setDate(formattedDateTime);

        chatRepo.save(chat);
    }

}
