package com.mia.BuhaiCommunications.service;

import com.mia.BuhaiCommunications.model.User;
import com.mia.BuhaiCommunications.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserService")
public class UserService {
    @Autowired
    public UserRepository userRepository;

    public User findById(Integer id) {
        return userRepository.findByUserId(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
