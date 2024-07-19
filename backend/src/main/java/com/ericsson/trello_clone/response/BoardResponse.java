package com.ericsson.trello_clone.response;

import com.ericsson.trello_clone.dto.BoardDto;
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
public class BoardResponse {
    @JsonProperty("boards")
    private List<BoardDto> boards;

    public static BoardResponse build(List<BoardDto> boards) {
        return new BoardResponse(Objects.requireNonNullElseGet(boards, ArrayList::new));
    }

    public BoardResponse boards(List<BoardDto> boards) {
        if (this.boards == null) {
            this.boards = new ArrayList<>();
        }
        this.boards.addAll(boards);
        return this;
    }
}
