package com.ericsson.trello_clone.repository;

import com.ericsson.trello_clone.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Group findByName(String name);

    List<Group> findAllByBoard(String board);
}
