package com.ericsson.trello_clone.service.impl;

import com.ericsson.trello_clone.domain.Board;
import com.ericsson.trello_clone.domain.User;
import com.ericsson.trello_clone.dto.BoardDto;
import com.ericsson.trello_clone.exceptions.EntityFoundInDatabaseException;
import com.ericsson.trello_clone.exceptions.EntityNotFoundInDatabaseException;
import com.ericsson.trello_clone.repository.BoardRepository;
import com.ericsson.trello_clone.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository repository;

    public Board getEntityFromDto(BoardDto boardDto) {
        Optional<Board> optionalBoard = repository.findById(boardDto.getId());

        return optionalBoard
                .orElseThrow(() -> new EntityNotFoundInDatabaseException("Board not found."));
    }

    @Override
    public Board create(BoardDto boardDto) {
        Board board = new Board();
        board.updateEntity(boardDto);
        return repository.save(board);
    }

    @Override
    public Board update(BoardDto boardDto) {
        Board board = getEntityFromDto(boardDto);
        board.updateEntity(boardDto);
        return repository.save(board);
    }

    @Override
    public void delete(Long boardId) {
        Board board = getById(boardId);
        repository.delete(board);
    }

    @Override
    public void addUserToBoard(Long boardId, User newUser) {
        Board board = getById(boardId);
        if (board == null) {
            throw new EntityNotFoundInDatabaseException("Board not found.");
        }

        if (newUser == null) {
            throw new EntityNotFoundInDatabaseException("User not found.");
        }

        Set<User> users = board.getUsers();
        if (!users.contains(newUser)) {
            users.add(newUser);
            board.setUsers(users);
            repository.save(board);
        } else {
            throw new EntityFoundInDatabaseException("User is already a member of the board.");
        }

        ModelMapper modelMapper = new ModelMapper();
        BoardDto boardDto = modelMapper.map(board, BoardDto.class);
        board.updateEntity(boardDto);
    }

    @Override
    public List<Board> getAllByUser(User user) {
        //TODO: implement
        return null;
    }

    @Override
    public List<Board> getAllBoards() {
        return repository.findAll().stream().sorted().toList();
    }

    @Override
    public Board getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundInDatabaseException("Board not found."));
    }
}
