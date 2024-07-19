package com.ericsson.trello_clone.domain;

import com.ericsson.trello_clone.dto.CardDto;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "cards")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;

    @ManyToOne
    private Group group;

    public void updateEntity(CardDto cardDto) {
        this.text = cardDto.getText();
        this.group = cardDto.getGroup();
    }
}
