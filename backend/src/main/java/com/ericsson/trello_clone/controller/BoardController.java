package com.ericsson.trello_clone.controller;

import com.ericsson.trello_clone.config.ApplicationRoles;
import com.ericsson.trello_clone.controller.helper.AvailablePaths;
import com.ericsson.trello_clone.controller.helper.CurrentUser;
import com.ericsson.trello_clone.domain.User;
import com.ericsson.trello_clone.dto.BoardDto;
import com.ericsson.trello_clone.exceptions.PermissionException;
import com.ericsson.trello_clone.jwt.principal.UserPrincipal;
import com.ericsson.trello_clone.response.BoardResponse;
import com.ericsson.trello_clone.service.BoardService;
import com.ericsson.trello_clone.service.UserDetailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@AllArgsConstructor
public class BoardController {
    private BoardService boardService;
    private UserDetailService userDetailService;

    private static void checkUserPermission(User user) {
        if (!(user.getRole().equals(ApplicationRoles.USER.getDatabaseName()))) {
            throw new PermissionException();
        }
    }

    private static void checkAdminPermission(User user) {
        if (!(user.getRole().equals(ApplicationRoles.ADMIN.getDatabaseName()))) {
            throw new PermissionException();
        }
    }

    @PostMapping(AvailablePaths.NEW_BOARD)
    public ResponseEntity<BoardDto> createBoard(@CurrentUser UserPrincipal userPrincipal, @RequestBody BoardDto boardDto) {
        log.info("Creating new board from admin account: {}", userPrincipal.getUsername());

        User user = userDetailService.getUserFromUserPrincipal(userPrincipal);

        checkAdminPermission(user);

        BoardDto result = BoardDto.build(boardService.create(boardDto, user));
        log.info("New board [{}] successfully created.", result.getName());

        return ResponseEntity.ok(result);
    }

    @PutMapping(AvailablePaths.USER_BOARD)
    public ResponseEntity<BoardDto> updateBoardFromUser(@CurrentUser UserPrincipal userPrincipal, @RequestBody BoardDto boardDto) {
        log.info("Updating board [{}] from account: {}", boardDto.getName(), userPrincipal.getUsername());
        User user = userDetailService.getUserFromUserPrincipal(userPrincipal);

        checkUserPermission(user);

        BoardDto result = BoardDto.build(boardService.update(boardDto, user));
        log.info("Board [{}] successfully updated.", result.getName());
        return ResponseEntity.ok(result);
    }

    @GetMapping(AvailablePaths.USER_BOARDS)
    public ResponseEntity<BoardResponse> getAllBoardsFromUser(@CurrentUser UserPrincipal userPrincipal) {
        log.info("Getting all boards for account: {}", userPrincipal.getUsername());
        User user = userDetailService.getUserFromUserPrincipal(userPrincipal);

        checkUserPermission(user);

        List<BoardDto> boardDtos = boardService.getAllByUser(user).stream()
                .map(BoardDto::build)
                .collect(Collectors.toList());
        return ResponseEntity.ok(BoardResponse.build(boardDtos));
    }

    @GetMapping(AvailablePaths.ALL_BOARDS)
    public ResponseEntity<BoardResponse> getAllBoards(@CurrentUser UserPrincipal userPrincipal) {
        log.info("Retrieving all boards");
        User user = userDetailService.getUserFromUserPrincipal(userPrincipal);

        checkAdminPermission(user);

        return ResponseEntity.ok(BoardResponse.build(boardService.getAllBoards().stream()
                .map(BoardDto::build)
                .collect(Collectors.toList())));

    }

    @PutMapping(AvailablePaths.BOARD)
    public void addUserToBoard(@PathVariable BoardDto boardDto, @PathVariable User newUser, @CurrentUser UserPrincipal userPrincipal) {
        log.info("Adding user [{}] to board [{}]", newUser.getUsername(), boardDto.getName());
        User user = userDetailService.getUserFromUserPrincipal(userPrincipal);

        checkAdminPermission(user);

        Long boardId = boardDto.getId();

        boardService.addUserToBoard(boardId, newUser);
    }
}
