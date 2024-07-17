package com.ericsson.trello_clone.domain;

import com.ericsson.trello_clone.dto.BoardDto;
import com.ericsson.trello_clone.dto.GroupDto;
import com.ericsson.trello_clone.dto.UserDto;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "boards")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    private User owner;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private Set<Group> groups;

    @ManyToMany(mappedBy = "boards")
    private Set<User> users;

    public void updateEntity(BoardDto boardDto) {
        this.name = boardDto.getName();
        this.owner = boardDto.getOwner().toEntity();
        this.groups = boardDto.getGroups().stream().map(GroupDto::toEntity).collect(Collectors.toSet());
        this.users = boardDto.getUsers().stream().map(UserDto::toEntity).collect(Collectors.toSet());
    }
}
