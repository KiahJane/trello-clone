package com.ericsson.trello_clone.service;

import com.ericsson.trello_clone.domain.Card;
import com.ericsson.trello_clone.dto.CardDto;
import org.springframework.stereotype.Service;

@Service
public interface CardService {

    Card create(CardDto cardDto);

    Card update(CardDto cardDto);

    void delete(Long groupId);
}
