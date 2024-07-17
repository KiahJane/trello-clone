package com.ericsson.trello_clone.repository;

import com.ericsson.trello_clone.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameOrEmail(String username, String email);

    User findByUsername(String username);

    User findByEmail(String email);

    User getById(Long id);

    Optional<User> findByUser(User user);
}
