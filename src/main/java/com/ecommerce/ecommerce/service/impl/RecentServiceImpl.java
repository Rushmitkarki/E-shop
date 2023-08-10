package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.RecentDto;
import com.ecommerce.ecommerce.entity.Item;
import com.ecommerce.ecommerce.entity.Recent;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.repo.RecentRepo;
import com.ecommerce.ecommerce.service.ItemService;
import com.ecommerce.ecommerce.service.RecentService;
import com.ecommerce.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RecentServiceImpl implements RecentService {
    private final RecentRepo recentRepo;

    private final ItemService itemService;
    private final UserService userService;

    private final  String filePath = System.getProperty("user.dir") + "/item_img/";

    @Override

    public void addToRecent(int id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.getByEmail(email).get();

        List<Recent> recentList = recentRepo.getRecent(user.getUserId());
        if(recentList.size()==3){
            recentRepo.delete(recentList.get(2));
        }
        recent(id);
    }

    private void recent(int id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.getByEmail(email).get();

        Recent recent=recentRepo.getByItemIdAndUserId(id,user.getUserId()).orElse(new Recent());
        Item item = itemService.getByIdNoOpt(id).orElse(null);
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        if(recent.getRecentId()==null){
            recent.setRecentDate(formattedDateTime);
            recent.setItem(item);
            recent.setUser(user);
            recentRepo.save(recent);
        }
        else{
            recent.setRecentDate(formattedDateTime);
            recentRepo.save(recent);
        }
    }

    @Override
    public List<Item> getRecentItems() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.getByEmail(email).get();
        List<Recent> recentList = recentRepo.getRecent(user.getUserId());
        return recentList.stream().map(recent -> Item.builder()
                .itemId(recent.getItem().getItemId())
                .itemPrice(recent.getItem().getItemPrice())
                .imageBase64(getImageBase64(recent.getItem().getItemImage()))
                .itemDescription(recent.getItem().getItemDescription())
                .build()
        ).toList();
    }

    private String getImageBase64(String fileName) {
        File file = new File(filePath + fileName);
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return Base64.getEncoder().encodeToString(bytes);
    }
}
