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

    void deleteById(Integer id);
}
