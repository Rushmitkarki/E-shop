package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.UserDto;
import com.ecommerce.ecommerce.entity.Notification;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.service.NotificationService;
import com.ecommerce.ecommerce.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final NotificationService notificationService;


    @GetMapping("view")
    public String viewUser(Model model){
        User user   = userService.getActiveUser().orElse(null);
        if (user == null){
            return "redirect:/login";
        }
        model.addAttribute("user",user);
        model.addAttribute("imageBase64" ,getImageBase64(user.getImage()));

        List<Notification> notification=notificationService.getNotification();
        model.addAttribute("notifications",notification);
        return "Profile";
    }

    @PostMapping("/update")
    public String updateUser(@Valid UserDto userDto) throws IOException {
        userService.updateProfile(userDto);
        return "redirect:/user/view";
    }

    @PostMapping("/delete")
    public String deleteUser(){
        userService.deleteAccount();
        return "redirect:/login";
    }

    @GetMapping("/about")
    public String about(Model model){
        User user   = userService.getActiveUser().orElse(null);
        if (user == null){
            return "redirect:/login";
        }
        model.addAttribute("user",user);
        List<Notification> notification=notificationService.getNotification();
        model.addAttribute("notifications",notification);
        return "AboutUs";
    }

    @GetMapping("/term")
    public String term(Model model){
        User user   = userService.getActiveUser().orElse(null);
        if (user == null){
            return "redirect:/login";
        }
        model.addAttribute("user",user);
        List<Notification> notification=notificationService.getNotification();
        model.addAttribute("notifications",notification);
        return "TermsAndCondition";
    }

    public String getImageBase64(String fileName) {
        String filePath = System.getProperty("user.dir") + "/profile_img/";
        File file = new File(filePath + fileName);
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        String base64 = Base64.getEncoder().encodeToString(bytes);
        return base64;
    }



    @GetMapping("/sendEmail")
    public String sendEmail(@RequestParam String email,Model model){
        this.userService.sendEmail(email);
        model.addAttribute("email",email);
        return "resetPassword";
    }

    @PostMapping("/resetPass")
    public String resetPassword(@RequestParam("email") String email,@RequestParam("password") String password,@RequestParam("otp") String otp,@RequestParam("cPassword") String cPass) throws IOException  {
        if(!password.equals(cPass)){
            return "redirect:/user/sendResetEmail/"+email+"?error=Password and Confirm Password must be same";
        }

        userService.resetPass(email,password,otp);
        return "redirect:/user/login";
    }

}
