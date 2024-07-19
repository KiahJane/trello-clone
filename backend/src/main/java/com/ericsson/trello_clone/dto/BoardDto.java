package com.ericsson.trello_clone.dto;

import com.ericsson.trello_clone.domain.Board;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@SuperBuilder
@Slf4j
public class BoardDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("owner")
    private UserDto owner;

    @JsonProperty("groups")
    private Set<GroupDto> groups;

    @JsonProperty("users")
    private Set<UserDto> users;

    public static BoardDto build(Board board) {
        return BoardDto.builder()
                .id(board.getId())
                .name(board.getName())
                .owner(UserDto.build(board.getOwner()))
                .groups(board.getGroups().stream().map(GroupDto::build).collect(Collectors.toSet()))
                .users(board.getUsers().stream().map(UserDto::build).collect(Collectors.toSet()))
                .build();
    }

    public static Set<BoardDto> build(Set<Board> boards) {
        return boards.stream().map(BoardDto::build).collect(Collectors.toSet());
    }

    public Board toEntity() {
        return Board.builder()
                .id(this.id)
                .name(this.name)
                .owner(this.owner.toEntity())
                .groups(this.groups.stream().map(GroupDto::toEntity).collect(Collectors.toSet()))
                .users(this.users.stream().map(UserDto::toEntity).collect(Collectors.toSet()))
                .build();
    }
}

