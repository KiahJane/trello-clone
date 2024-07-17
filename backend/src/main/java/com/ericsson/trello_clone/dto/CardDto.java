package com.ericsson.trello_clone.dto;


import com.ericsson.trello_clone.domain.Card;
import com.ericsson.trello_clone.domain.Group;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

@Data
@SuperBuilder
@Slf4j
public class CardDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("text")
    private String text;

    @JsonProperty("group")
    private Group group;

    public static CardDto build(Card card) {
        return CardDto.builder()
                .id(card.getId())
                .text(card.getText())
                .group(card.getGroup())
                .build();
    }

    public Card toEntity() {
        return Card.builder()
                .id(this.id)
                .text(this.text)
                .group(this.group)
                .build();
    }
}
