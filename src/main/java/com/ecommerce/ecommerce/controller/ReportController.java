package com.ecommerce.ecommerce.controller;


import com.ecommerce.ecommerce.dto.ReportDto;
import com.ecommerce.ecommerce.entity.Item;
import com.ecommerce.ecommerce.entity.Notification;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.service.ItemService;
import com.ecommerce.ecommerce.service.NotificationService;
import com.ecommerce.ecommerce.service.ReportService;
import com.ecommerce.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;
    private final ItemService itemService;
    private final UserService userService;
    private final NotificationService notificationService;

    @GetMapping("/{id}")
    public String reportItem(@PathVariable int id, Model model){
        Item item = itemService.getByIdNoOpt(id).get();
        model.addAttribute("item",item);
        User user = userService.getActiveUser().orElse(null);
        System.out.println(user);
        if (user == null){
            return "redirect:/login";
        }
        model.addAttribute("user",user);
        List<Notification> notification=notificationService.getNotification();
        model.addAttribute("notifications",notification);
        return "Report";
    }

    @PostMapping("/save")
    public String saveReport(ReportDto reportDto){
        reportService.reportItem(reportDto);
        return "redirect:/buyer/item/"+reportDto.getItemId();
    }
}
