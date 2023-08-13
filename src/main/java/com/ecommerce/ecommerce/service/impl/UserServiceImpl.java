package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.config.PasswordEncoderUtil;

import com.ecommerce.ecommerce.dto.UserDto;
import com.ecommerce.ecommerce.entity.Bill;
import com.ecommerce.ecommerce.entity.Cart;
import com.ecommerce.ecommerce.entity.Comment;
import com.ecommerce.ecommerce.entity.Item;
import com.ecommerce.ecommerce.entity.Rating;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.entity.Otp;
import com.ecommerce.ecommerce.repo.*;
import com.ecommerce.ecommerce.service.UserService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/profile_img";
    private final ItemRepo itemRepo;
    private final CartRepo cartRepo;
    private final BillRepo billRepo;
    private final RatingRepo ratingRepo;
    private final CommentRepo commentRepo;
    private final OtpRepo otpRepo;
    private static final ModelMapper modelMapper = new ModelMapper();
    private final JavaMailSender getJavaMailSender;
    private final EmailCredRepo emailCredRepo;
    private final ThreadPoolTaskExecutor taskExecutor;
    @Autowired
    @Qualifier("emailConfigBean")
    private Configuration emailConfig;

    @Override
    public void registerUser(UserDto userDto) {
        User user = userRepo.findByDeletedEmail(userDto.getEmail()).orElse(new User());
        user.setStatus("ACTIVE");
        user.setFname(userDto.getFname());
        user.setLname(userDto.getLname());
        user.setEmail(userDto.getEmail());
        user.setPassword(PasswordEncoderUtil.getInstance().encode(userDto.getPassword()));
        user.setSq(userDto.getSq());
        user.setRole(userDto.getRole());
        user.setDeleted(false);
        userRepo.save(user);
    }

    @Override
    public void updateProfile(UserDto userDto) throws IOException {
        User user = getActiveUser().orElse(null);

        if (user != null) {
            user.setFname(userDto.getFname());
            user.setLname(userDto.getLname());
            user.setEmail(userDto.getEmail());
            user.setAddress(userDto.getAddress());
            user.setPhoneNumber(userDto.getPhoneNumber());

            if (userDto.getImage() != null) {
                String imageName = user.getFname() + "_" + "profile";

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
        } else {
            System.out.println("User not found");
        }

    }

    @Override
    public List<User> getData() {
        return userRepo.findAll();
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
        UserDto userDto = new UserDto();
        user.setStatus("ACTIVE");
        user.setFname(userDto.getFname());
        user.setLname(userDto.getLname());
        user.setEmail(userDto.getEmail());
        user.setPassword(PasswordEncoderUtil.getInstance().encode(userDto.getPassword()));

        userRepo.save(user);

    }


    @Override
    public void verifyUser(String email, String citizenshipNumber) {
        User user = userRepo.findByEmail(email).orElse(null);
        if (user != null) {
            user.setCitizenshipNumber(citizenshipNumber);
            userRepo.save(user);
        } else {
            System.out.println("User not found");
        }
    }

    @Override
    public void verifySeller(String email, String panNumber) {
        User user = userRepo.findByEmail(email).orElse(null);
        if (user != null) {
            user.setPanNumber(panNumber);
            userRepo.save(user);
        } else {
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
        User user = getActiveUser().get();
        user.setDeleted(true);
        userRepo.save(user);
    }

    @Override
    public void sendEmail(String email) {
        try {
            User user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

            Map<String, String> model = new HashMap<>();
            model.put("name", user.getFname() + " " + user.getLname());
            String otpCode= generateRandomNumber();
            Otp otp = otpRepo.findByEmail(email).orElse(new Otp());
            otp.setEmail(email);
            otp.setOtp(otpCode);
            otp.setDate(getDate());
            otpRepo.save(otp);
            model.put("otp", otpCode);

            MimeMessage message = getJavaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

            Template template = emailConfig.getTemplate("resetPass.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setText(html, true);
            mimeMessageHelper.setSubject("Reset Password");
            mimeMessageHelper.setFrom("eshop6371@gmail.com");;


            taskExecutor.execute(new Thread() {
                public void run() {
                    getJavaMailSender.send(message);
                }
            });
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static String generateRandomNumber() {
        Random random = new Random();

        int otp=100000 + random.nextInt(900000);
        return String.valueOf(otp);
        // Generates a random number between 100000 and 999999 (inclusive)
    }

    @Override
    public void resetPass(String email, String password, String Otp) {
        Otp otp1 = otpRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("Email not found"));
        if (otp1.getOtp().equals(Otp) && otp1.getDate().isAfter(LocalDateTime.now())) {
            User user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
            user.setPassword(PasswordEncoderUtil.getInstance().encode(password));
            userRepo.save(user);
        } else {
            throw new RuntimeException("Invalid OTP");
        }
    }

    public LocalDateTime getDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.plusHours(1);
    }




}
