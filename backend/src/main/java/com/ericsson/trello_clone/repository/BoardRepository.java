package com.ericsson.trello_clone.repository;

import com.ericsson.trello_clone.domain.Board;
import com.ericsson.trello_clone.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findByName(String name);

    Optional<Board> findByOwner(User owner);

    Optional<Board> findById(Long id);
}
