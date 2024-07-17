package com.ericsson.trello_clone.repository;

import com.ericsson.trello_clone.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findByName(String name);

    Board findByOwner(String owner);
}
