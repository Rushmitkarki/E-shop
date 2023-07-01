package com.ecommerce.ecommerce.repo;

import com.ecommerce.ecommerce.entity.User;
import org.junit.jupiter.api.*;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;


@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRepoTest {

    @Autowired
    private UserRepo userRepo;

//    JUnit to save user
    @Test
    @Order(1)
    @Rollback(value = false)

    public void saveUserTest() {
        User user = User.builder()
                .fname("Test")
                .lname("User")
                .email("u@gmail.com")
                .password("123")
                .role("USER")

                .build();
        userRepo.save(user);

        Assertions.assertThat(user.getUserId()).isGreaterThan(0);
    }

//    JUnit to find user by email
    @Test
    @Order(2)
    public void findByEmailTest() {
        String email = "u@gmail.com";
        User user = userRepo.findByEmail(email).get();
        Assertions.assertThat(user.getEmail()).isEqualTo("u@gmail.com");

    }

//    JUnit to delete user
    @Test
    @Order(3)
    @Rollback(value = false)
    public void deleteUserTest() {
        String email = "u@gmail.com";
        User user = userRepo.findByEmail(email).get();
        userRepo.delete(user);

        User user1=null;

        Optional<User> optionalUser = userRepo.findByEmail(email);

        if(optionalUser.isPresent()){
            user1=optionalUser.get();
        }

        Assertions.assertThat(user1).isNull();
    }
}