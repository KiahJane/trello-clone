package com.ericsson.trello_clone.service;

import com.ericsson.trello_clone.model.Board;
import com.ericsson.trello_clone.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    public Board save(Board board) {
        return boardRepository.save(board);
    }
}
