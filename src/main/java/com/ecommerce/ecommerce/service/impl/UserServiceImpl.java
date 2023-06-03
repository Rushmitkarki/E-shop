package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.config.PasswordEncoderUtil;
import com.ecommerce.ecommerce.dto.UserDto;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.repo.UserRepo;
import com.ecommerce.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    @Override
    public void registerUser(UserDto userDto) {
        User user=new User();
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(PasswordEncoderUtil.getInstance().encode(userDto.getPassword()));
        user.setUserRole(userDto.getUserRole());
        userRepo.save(user);

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
    public void deleteById(Integer id) {

    }
}
