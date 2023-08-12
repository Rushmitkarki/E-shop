package com.ecommerce.ecommerce.repo;

import com.ecommerce.ecommerce.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpRepo extends JpaRepository<Otp,Integer> {

    @Query(value = "select * from otp where email = ?1",nativeQuery = true)
    Optional<Otp> findByEmail(String email);
}
