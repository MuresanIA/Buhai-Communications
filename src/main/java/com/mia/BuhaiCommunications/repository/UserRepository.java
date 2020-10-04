package com.mia.BuhaiCommunications.repository;

import com.mia.BuhaiCommunications.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByUserId(Integer userId);
    Optional<User> findByUserName(String username);
}
