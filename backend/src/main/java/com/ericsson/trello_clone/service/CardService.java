package com.ericsson.trello_clone.service;

import com.ericsson.trello_clone.model.Card;
import com.ericsson.trello_clone.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    public Card save(Card card) {
        return cardRepository.save(card);
    }
}
