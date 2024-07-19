package com.ericsson.trello_clone.repository;

import com.ericsson.trello_clone.domain.Card;
import com.ericsson.trello_clone.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    Optional<Card> findByText(String text);

    List<Card> findAllByGroup(Group group);
}
