package com.ericsson.trello_clone.service.impl;

import com.ericsson.trello_clone.domain.Card;
import com.ericsson.trello_clone.dto.CardDto;
import com.ericsson.trello_clone.exceptions.EntityNotFoundInDatabaseException;
import com.ericsson.trello_clone.repository.CardRepository;
import com.ericsson.trello_clone.service.CardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository repository;

    public Card getEntityFromDto(CardDto cardDto) {
        Optional<Card> optionalCard = repository.findById(cardDto.getId());

        return optionalCard
                .orElseThrow(() -> new EntityNotFoundInDatabaseException("Card not found."));
    }

    @Override
    public Card create(CardDto cardDto) {
        Card card = new Card();
        card.updateEntity(cardDto);
        return repository.save(card);
    }

    @Override
    public Card update(CardDto cardDto) {
        Card card = getEntityFromDto(cardDto);
        card.updateEntity(cardDto);
        return repository.save(card);
    }

    @Override
    public void delete(Long cardId) {
        Card card = repository.findById(cardId)
                .orElseThrow(() -> new EntityNotFoundInDatabaseException("Card not found in database."));
        repository.delete(card);
    }
}
