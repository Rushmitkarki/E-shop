package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.UserDto;
import com.ecommerce.ecommerce.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("view")
    public String viewUser(Model model){
        model.addAttribute("user",userService.getActiveUser());
        return "Profile";
    }

    @PostMapping("/update")
    public String updateUser(@Valid UserDto userDto) throws IOException {
        userService.updateProfile(userDto);
        return "redirect:/user/view";
    }
}
