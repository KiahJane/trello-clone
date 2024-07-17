package com.ericsson.trello_clone.controller;

import com.ericsson.trello_clone.controller.helper.AvailablePaths;
import com.ericsson.trello_clone.model.Board;
import com.ericsson.trello_clone.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {
    @Autowired
    private BoardService boardService;

    @PostMapping(AvailablePaths.NEW_BOARD)
    public Board createBoard(@RequestBody Board board) {
        return boardService.save(board);
    }

}
