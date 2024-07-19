package com.ericsson.trello_clone.domain;

import com.ericsson.trello_clone.dto.CardDto;
import com.ericsson.trello_clone.dto.GroupDto;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "groups")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    private Board board;

    @ManyToOne
    private User owner;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private Set<Card> cards;

    public void updateEntity(GroupDto groupDto) {
        this.name = groupDto.getName();
        this.board = groupDto.getBoard().toEntity();
        this.owner = groupDto.getOwner().toEntity();
        this.cards = groupDto.getCards().stream().map(CardDto::toEntity).collect(Collectors.toSet());
    }
}
