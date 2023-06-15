package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.dto.UserDto;
import com.ecommerce.ecommerce.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void registerUser(UserDto userDto);
    List<User> getData();
    Optional<User> getById(Integer id);
    User getByIdNoOpt(Integer id);

    Optional<User> getByEmail(String email);
    void deleteById(Integer id);

    void setSession(User user);

    void verifyUser(String email,String citizenshipNumber);

    void verifySeller(String email,String panNumber);

    Optional<User> getActiveUser();
}
