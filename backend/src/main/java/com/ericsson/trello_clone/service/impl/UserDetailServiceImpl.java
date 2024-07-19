package com.ericsson.trello_clone.service.impl;

import com.ericsson.trello_clone.domain.User;
import com.ericsson.trello_clone.dto.UserDto;
import com.ericsson.trello_clone.exceptions.EntityNotFoundInDatabaseException;
import com.ericsson.trello_clone.jwt.principal.UserPrincipal;
import com.ericsson.trello_clone.repository.UserRepository;
import com.ericsson.trello_clone.service.UserDetailService;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailService {

    private final UserRepository repository;

    public UserDetailServiceImpl(@NotNull UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user;

        try {
            user = repository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
        } catch (EmptyResultDataAccessException ex) {
            throw new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail);
        }

        return UserPrincipal.buildPrincipal(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user;

        Optional<User> optionalUser = repository.findById(id);
        if (optionalUser.isPresent())
            user = optionalUser.get();
        else
            throw new UsernameNotFoundException("User not found with id : " + id);

        return UserPrincipal.buildPrincipal(user);
    }

    @Override
    public User getUserById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundInDatabaseException(this.getClass().getName(), id));
    }

    @Override
    public User getUserByEmail(String email) {
        return repository.findByEmail(email);
    }
    @Override
    public boolean userExistsByUsername(String username) {
        User user = repository.findByUsername(username);
        return user != null;
    }

    @Override
    public boolean userExistsByEmail(String email) {
        User user = repository.findByEmail(email);
        return user != null;
    }

    @Override
    public User createUser(User user) {
        return repository.save(user);
    }

    @Override
    public User updateUser(UserDto userDto) {
        User user = getUserFromUserDto(userDto);
        user.updateEntity(userDto);
        return repository.save(user);
    }

    @Override
    public User getUserFromUserPrincipal(UserPrincipal userPrincipal) {
        Optional<User> optionalUser = repository.findById(userPrincipal.getId());

        return optionalUser.orElseGet(() -> repository.findByEmail(userPrincipal.getEmail()));
    }

    @Override
    public User getUserFromUserDto(UserDto userDto) {
        Optional<User> optionalUser = repository.findById(userDto.getId());

        return optionalUser.orElseGet(() -> repository.findByEmail(userDto.getEmail()));
    }
}

