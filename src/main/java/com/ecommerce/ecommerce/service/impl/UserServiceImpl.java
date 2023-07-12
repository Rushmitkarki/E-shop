package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.config.PasswordEncoderUtil;
import com.ecommerce.ecommerce.dto.ItemDto;
import com.ecommerce.ecommerce.dto.UserDto;
import com.ecommerce.ecommerce.entity.Bill;
import com.ecommerce.ecommerce.entity.Cart;
import com.ecommerce.ecommerce.entity.Comment;
import com.ecommerce.ecommerce.entity.Item;
import com.ecommerce.ecommerce.entity.Rating;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.repo.*;
import com.ecommerce.ecommerce.service.UserService;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    private static final ModelMapper modelMapper = new ModelMapper();

    @Override
    public void registerUser(UserDto userDto) {
        User user = new User();
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
        UserDto userDto = new UserDto();
        user.setStatus("ACTIVE");
        user.setFname(userDto.getFname());
        user.setLname(userDto.getLname());
        user.setEmail(userDto.getEmail());
        user.setPassword(PasswordEncoderUtil.getInstance().encode(userDto.getPassword()));

        userRepo.save(user);

    }

    @Override
    public void addToItem(int userId, ItemDto itemDto) {

        User user=userRepo.findById(userId).orElse(null);
        if(user!=null){
            Item item=mapToItem(itemDto);
            user.addToItem(item);
            userRepo.save(user);

        }
    }

    @Override
    public void removeFromItem(int userId, int itemId) {
        User user=userRepo.findById(userId).orElse(null);
        if(user!=null){
            Item itemToRemove=user.getItems().stream()
                    .filter(item -> item.getItemId().equals(itemId))
                    .findFirst().orElse(null);
            if(itemToRemove!=null){
                user.getItems().remove(itemToRemove);
                userRepo.save(user);
            }
        }
    }

    @Override
    public UserDto mapToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public Item mapToItem(ItemDto itemDto) {
        return modelMapper.map(itemDto, Item.class);
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

        // Check if the user is a buyer
        if (user.getRole().equals("Buyer")) {
            // Delete the buyer's carts
            List<Cart> carts = cartRepo.findByUserId(user.getUserId());

            for (Cart cart : carts) {
                cartRepo.delete(cart);
            }

            // Delete the buyer's bills
            List<Bill> bills = billRepo.getByUserId(user.getUserId());

            for (Bill bill : bills) {
                billRepo.delete(bill);
            }

            List<Rating> ratings = ratingRepo.getByUserId(user.getUserId());
            for (Rating rating : ratings) {
                ratingRepo.delete(rating);
            }

            List<Comment> comments = commentRepo.getByUserId(user.getUserId());
            for (Comment comment : comments) {
                commentRepo.delete(comment);
            }

            // Delete the buyer
            userRepo.delete(user);
        } else if (user.getRole().equals("Seller")) {
            // Delete the seller's items
            List<Item> items = itemRepo.getBySellerId(user.getUserId());

            for (Item item : items) {
                List<Cart> carts = cartRepo.getByItemId(item.getItemId());
                for (Cart cart : carts) {
                    cartRepo.delete(cart);
                }

                List<Rating> ratings = ratingRepo.getByItemId(item.getItemId());
                for (Rating rating : ratings) {
                    ratingRepo.delete(rating);
                }

                List<Comment> comments = commentRepo.getCommentsByItemId(item.getItemId());
                for (Comment comment : comments) {
                    commentRepo.delete(comment);
                }
                itemRepo.delete(item);
            }

            // Delete the seller
            userRepo.delete(user);
        } else {
            // The user is not a buyer or a seller
            System.out.println("The user is not a buyer or a seller");
        }
    }

}
