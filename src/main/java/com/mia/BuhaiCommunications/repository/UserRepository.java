package com.mia.BuhaiCommunications.repository;

import com.mia.BuhaiCommunications.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
