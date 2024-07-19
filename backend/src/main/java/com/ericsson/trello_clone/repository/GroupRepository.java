package com.ericsson.trello_clone.repository;

import com.ericsson.trello_clone.domain.Board;
import com.ericsson.trello_clone.domain.Group;
import com.ericsson.trello_clone.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Optional<Group> findByName(String name);

    List<Group> findAllByBoard(Board board);

    List<Group> findAllByOwner(User owner);
}
