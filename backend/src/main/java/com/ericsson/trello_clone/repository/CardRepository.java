package com.ericsson.trello_clone.repository;

import com.ericsson.trello_clone.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    Card findByText(String text);

    List<Card> findAllByGroup(String group);
}
