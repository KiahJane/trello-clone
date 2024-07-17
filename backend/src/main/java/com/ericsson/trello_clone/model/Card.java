package com.ericsson.trello_clone.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;

    @ManyToOne
    private Group group;
}
