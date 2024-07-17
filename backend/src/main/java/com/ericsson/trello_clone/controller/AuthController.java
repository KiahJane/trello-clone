package com.ericsson.trello_clone.controller;

import com.ericsson.trello_clone.controller.helper.AvailablePaths;
import com.ericsson.trello_clone.model.User;
import com.ericsson.trello_clone.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping(AvailablePaths.REGISTER)
    public User register(@RequestBody User user) {
        return userService.save(user);
    }

    @PostMapping(AvailablePaths.AUTH_LOG_IN)
    public User login(@RequestBody User user) {
        User existingUser = userService.findByUsername(user.getUsername());
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            return existingUser;
        }
        // TODO: add proper error handling
        return null;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
