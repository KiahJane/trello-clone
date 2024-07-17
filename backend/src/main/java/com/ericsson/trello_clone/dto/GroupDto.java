package com.ericsson.trello_clone.dto;

import com.ericsson.trello_clone.domain.Group;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@SuperBuilder
@Slf4j
public class GroupDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("board")
    private BoardDto board;

    @JsonProperty("owner")
    private UserDto owner;

    @JsonProperty("cards")
    private Set<CardDto> cards;

    public static GroupDto build(Group group) {
        return GroupDto.builder()
                .id(group.getId())
                .name(group.getName())
                .board(BoardDto.build(group.getBoard()))
                .owner(UserDto.build(group.getOwner()))
                .cards(group.getCards().stream().map(CardDto::build).collect(Collectors.toSet()))
                .build();
    }

    public static Set<GroupDto> build(Set<Group> groups) {
        return groups.stream().map(GroupDto::build).collect(Collectors.toSet());
    }

    public Group toEntity() {
        return Group.builder()
                .id(this.id)
                .name(this.name)
                .board(this.board.toEntity())
                .owner(this.owner.toEntity())
                .cards(this.cards.stream().map(CardDto::toEntity).collect(Collectors.toSet()))
                .build();
    }
}
