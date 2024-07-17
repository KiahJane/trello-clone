package com.ericsson.trello_clone.controller;

import com.ericsson.trello_clone.domain.Card;
import com.ericsson.trello_clone.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {
    @Autowired
    private CardService cardService;

    public Card createCard(@RequestBody Card card) {
        return cardService.save(card);
    }
}
