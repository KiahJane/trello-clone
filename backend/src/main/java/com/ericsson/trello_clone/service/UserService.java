package com.ericsson.trello_clone.service;

import com.ericsson.trello_clone.domain.User;
import com.ericsson.trello_clone.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getAllUsers();

    void saveUserInformationFromAdmin(UserDto dto);

    List<String> getAllRoles();
}
