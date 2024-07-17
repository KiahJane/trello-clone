package com.ericsson.trello_clone.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    private User owner;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private Set<Group> groups;
}
