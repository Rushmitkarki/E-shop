package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.UserDto;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.repo.UserRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceImplTest {
    @Autowired
    private UserRepo userRepo;

    // JUnit to register User
    @Test
    @Order(1)

    @Rollback(value = false)
    public void registerUser() {
        User user = User.builder()
                .fname("Test")
                .lname("User")
                .email("s@gmail.com")
                .password("test")
                .role("USER")
                .status("ACTIVE")
                .sq("Momo")
                .build();
        userRepo.save(user);

        Assertions.assertThat(user.getUserId()).isGreaterThan(0);

    }

    @Test
    @Order(2)
    void getByEmail() {
        String email = "t@gmail.com";
        User user = userRepo.findByEmail(email).get();
        Assertions.assertThat(user.getEmail()).isEqualTo(email);
    }

}