package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.Validation.CitizenshipValidation;
import com.ecommerce.ecommerce.dto.UserDto;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.service.UserService;
//import com.example.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "register";
    }


    @PostMapping("/save")
    public String registerUserAccount(
             @Valid UserDto registrationDto,
            BindingResult result
    ) {

        userService.registerUser(registrationDto);
        return "redirect:/verify/"+registrationDto.getEmail();
    }
    @GetMapping("/verify/{email}")
    public String verifyUser(Model model, @PathVariable String email){

        model.addAttribute("email", email);

        return "Verify";
    }

    @PostMapping("/verify/{email}")
    public String verifyUser(@PathVariable String email,  @RequestParam("citizenshipNumber") String citizenshipNumber){
//        System.out.println(userDto.getCitizenshipNumber());
        if(!CitizenshipValidation.validate(citizenshipNumber)){
            return "redirect:/verify/{email}?error";
        }

        userService.verifyUser(email,citizenshipNumber);
        User user = userService.getByEmail(email).orElse(null);
        if(user==null){
            return "redirect:/verify/{email}?error";
        }
        if(user.getRole().equals("Seller")){
            System.out.println(user.getRole());
            return "redirect:/seller/verify/{email}";
        }
        return "redirect:/login?success";
    }
}
