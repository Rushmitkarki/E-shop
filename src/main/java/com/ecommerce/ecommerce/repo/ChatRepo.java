package com.ecommerce.ecommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ecommerce.entity.Chat;

public interface ChatRepo extends JpaRepository<Chat, Integer> {

}
