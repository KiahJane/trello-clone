package com.ericsson.trello_clone.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    private Board board;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private Set<Card> cards;
}
