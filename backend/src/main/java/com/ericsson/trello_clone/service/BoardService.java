package com.ericsson.trello_clone.service;

import com.ericsson.trello_clone.domain.Board;
import com.ericsson.trello_clone.domain.User;
import com.ericsson.trello_clone.dto.BoardDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BoardService {
    Board create(BoardDto boardDto, User user);
    Board update(BoardDto boardDto, User user);
    Board getById(Long id);
    void addUserToBoard(Long boardId, User newUser);
    List<Board> getAllByUser(User user);
    List<Board> getAllBoards();
}
