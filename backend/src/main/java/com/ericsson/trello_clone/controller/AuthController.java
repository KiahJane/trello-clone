package com.ericsson.trello_clone.controller;

import com.ericsson.trello_clone.controller.helper.AvailablePaths;
import com.ericsson.trello_clone.model.User;
import com.ericsson.trello_clone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//TODO: possibly rename to UserController & place diff code in "AuthController"
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
