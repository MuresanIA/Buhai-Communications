package com.mia.BuhaiCommunications.controller.rest;

import com.mia.BuhaiCommunications.model.User;
import com.mia.BuhaiCommunications.model.UserList;
import com.mia.BuhaiCommunications.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    public UserRepository userRepository;

    @GetMapping("/users")
    public UserList showAllUsers() {
        return new UserList(userRepository.findAll());
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping("/users")
    public void deleteUser(@RequestBody User user) {
        userRepository.deleteById(user.getUserId());
    }
}
