package com.ericsson.trello_clone.service.impl;

import com.ericsson.trello_clone.config.ApplicationRoles;
import com.ericsson.trello_clone.domain.User;
import com.ericsson.trello_clone.dto.UserDto;
import com.ericsson.trello_clone.repository.UserRepository;
import com.ericsson.trello_clone.service.UserService;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(@NotNull UserRepository repository) {
        this.repository = repository;
    }

    private static Comparator<User> userComparator() {
        return Comparator.comparing(User::getUsername)
                .thenComparing(User::getEmail)
                .thenComparing(User::getRole);
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll().stream()
                .sorted(userComparator()).toList();
    }

    @Override
    public void saveUserInformationFromAdmin(UserDto userDto) {
        User user = getEntityFromDto(userDto);
        boolean isApproved = !user.getApprovedByAdmin() && userDto.getApprovedByAdmin();
        boolean isDisproved = user.getApprovedByAdmin() && !userDto.getApprovedByAdmin();

        user.updateEntity(userDto);

        if (isApproved) {
            user.setRole(ApplicationRoles.USER.getDatabaseName());
        } else if (isDisproved) {
            user.setRole(ApplicationRoles.UNAPPROVED_USER.getDatabaseName());
        }

        repository.save(user);
    }

    @Override
    public List<String> getAllRoles() {
        return List.of(ApplicationRoles.USER.getDatabaseName(), ApplicationRoles.UNAPPROVED_USER.getDatabaseName(), ApplicationRoles.ADMIN.getDatabaseName());
    }

    public User getEntityFromDto(UserDto userDto) {
        Optional<User> optionalUser = repository.findById(userDto.getId());

        return optionalUser.orElseGet(() -> repository.findByEmail(userDto.getEmail()));
    }
}

