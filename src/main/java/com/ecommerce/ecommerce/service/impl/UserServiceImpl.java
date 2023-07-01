package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.config.PasswordEncoderUtil;
import com.ecommerce.ecommerce.dto.UserDto;

import com.ecommerce.ecommerce.entity.User;

import com.ecommerce.ecommerce.repo.UserRepo;
import com.ecommerce.ecommerce.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/profile_img";


    @Override
    public void registerUser(UserDto userDto) {
        User user=new User();
        user.setStatus("ACTIVE");
        user.setFname(userDto.getFname());
        user.setLname(userDto.getLname());
        user.setEmail(userDto.getEmail());
        user.setPassword(PasswordEncoderUtil.getInstance().encode(userDto.getPassword()));
        user.setSq(userDto.getSq());
        user.setRole(userDto.getRole());
        userRepo.save(user);
        }



        @Override
    public void updateProfile(UserDto userDto) throws IOException {
        User user=getActiveUser().orElse(null);

        if(user!=null){
            user.setFname(userDto.getFname());
            user.setLname(userDto.getLname());
            user.setEmail(userDto.getEmail());
            user.setAddress(userDto.getAddress());
            user.setPhoneNumber(userDto.getPhoneNumber());

            if(userDto.getImage()!=null){
                String imageName = user.getFname()+"_" + "profile";

                String originalFilename = userDto.getImage().getOriginalFilename();
                String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));

                StringBuilder fileNames = new StringBuilder();
                System.out.println(UPLOAD_DIRECTORY);
                Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, imageName + fileExtension);
                fileNames.append(imageName + fileExtension);
                Files.write(fileNameAndPath, userDto.getImage().getBytes());

                user.setImage(imageName + fileExtension);
            }
            userRepo.save(user);
        }
        else{
            System.out.println("User not found");
        }

    }


    @Override
    public List<User> getData() {
        return null;
    }

    @Override
    public Optional<User> getById(Integer id) {
        return Optional.empty();
    }


    @Override
    public User getByIdNoOpt(Integer id) {
        return null;
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void setSession(User user) {
        UserDto userDto=new UserDto();
        user.setStatus("ACTIVE");
        user.setFname(userDto.getFname());
        user.setLname(userDto.getLname());
        user.setEmail(userDto.getEmail());
        user.setPassword(PasswordEncoderUtil.getInstance().encode(userDto.getPassword()));


        userRepo.save(user);

    }

    @Override
    public void verifyUser(String email,String citizenshipNumber) {
        User user= userRepo.findByEmail(email).orElse(null);
        if(user!=null){
           user.setCitizenshipNumber(citizenshipNumber);
            userRepo.save(user);
        }
        else{
            System.out.println("User not found");
        }
    }

    @Override
    public void verifySeller(String email, String panNumber) {
        User user= userRepo.findByEmail(email).orElse(null);
        if(user!=null){
            user.setPanNumber(panNumber);
            userRepo.save(user);
        }
        else{
            System.out.println("User not found");
        }
    }

    @Override
    public Optional<User> getActiveUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return Optional.of(getByEmail(email).orElse(new User()));
    }

    @Override
    public void deleteAccount() {
        User user=getActiveUser().get();
        userRepo.delete(user);
    }






}
