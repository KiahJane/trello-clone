package com.ericsson.trello_clone.response;

import com.ericsson.trello_clone.dto.CardDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class CardResponse {
    @JsonProperty("cards")
    private List<CardDto> cards;

    public static CardResponse build(List<CardDto> cards) {
        return new CardResponse(Objects.requireNonNullElseGet(cards, ArrayList::new));
    }

    public CardResponse cards(List<CardDto> cards) {
        if (this.cards == null) {
            this.cards = new ArrayList<>();
        }
        this.cards.addAll(cards);
        return this;
    }
}
