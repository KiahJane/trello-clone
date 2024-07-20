package com.ericsson.trello_clone.controller;

import com.ericsson.trello_clone.controller.helper.AvailablePaths;
import com.ericsson.trello_clone.controller.helper.CurrentUser;
import com.ericsson.trello_clone.domain.User;
import com.ericsson.trello_clone.dto.BoardDto;
import com.ericsson.trello_clone.jwt.principal.UserPrincipal;
import com.ericsson.trello_clone.response.ApiResponse;
import com.ericsson.trello_clone.response.BoardResponse;
import com.ericsson.trello_clone.service.BoardService;
import com.ericsson.trello_clone.service.UserDetailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.ericsson.trello_clone.utils.CheckPermissionUtils.*;

@Slf4j
@RestController
@AllArgsConstructor
public class BoardController {
    private BoardService boardService;
    private UserDetailService userDetailService;

    @PostMapping(AvailablePaths.NEW_BOARD)
    public ResponseEntity<BoardDto> createBoardAdmin(@CurrentUser UserPrincipal userPrincipal, @RequestBody BoardDto boardDto) {
        log.info("Creating new board from admin account: {}", userPrincipal.getUsername());

        User user = userDetailService.getUserFromUserPrincipal(userPrincipal);

        checkAdminPermission(user);

        BoardDto result = BoardDto.build(boardService.create(boardDto));
        log.info("New board [{}] successfully created.", result.getName());

        return ResponseEntity.ok(result);
    }

    @PutMapping(AvailablePaths.USER_BOARD)
    public ResponseEntity<BoardDto> updateBoardUser(@PathVariable Long boardId, @CurrentUser UserPrincipal userPrincipal, @RequestBody BoardDto boardDto) {
        log.info("Updating board [{}] from account: {}", boardDto.getName(), userPrincipal.getUsername());
        User user = userDetailService.getUserFromUserPrincipal(userPrincipal);

        checkUserPermission(user);

        BoardDto result = BoardDto.build(boardService.update(boardDto));
        log.info("Board [{}] successfully updated.", result.getName());
        return ResponseEntity.ok(result);
    }

    @PutMapping(AvailablePaths.ADMIN_BOARD)
    public void addUserToBoard(@PathVariable Long boardId, @PathVariable User newUser, @CurrentUser UserPrincipal userPrincipal) {
        log.info("Adding user [{}] to board [{}]", newUser.getUsername(), boardId);
        User user = userDetailService.getUserFromUserPrincipal(userPrincipal);

        checkAdminPermission(user);

        boardService.addUserToBoard(boardId, newUser);
    }

    @DeleteMapping(AvailablePaths.ADMIN_BOARD)
    public ResponseEntity<ApiResponse> deleteBoardAdmin(@PathVariable Long boardId, @CurrentUser UserPrincipal userPrincipal) {
        log.info("Deleting board [{}]", boardId);
        User user = userDetailService.getUserFromUserPrincipal(userPrincipal);

        checkAdminPermission(user);

        boardService.delete(boardId);
        log.info("Board [{}] successfully deleted.", boardId);
        return ResponseEntity.ok(new ApiResponse(Boolean.TRUE, "Successfully deleted board."));
    }

    @GetMapping(AvailablePaths.USER_BOARDS)
    public ResponseEntity<BoardResponse> getAllBoardsUser(@CurrentUser UserPrincipal userPrincipal) {
        log.info("Getting all boards for account: {}", userPrincipal.getUsername());
        User user = userDetailService.getUserFromUserPrincipal(userPrincipal);

        checkUserPermission(user);

        List<BoardDto> boardDtos = boardService.getAllByUser(user).stream()
                .map(BoardDto::build)
                .collect(Collectors.toList());
        return ResponseEntity.ok(BoardResponse.build(boardDtos));
    }

    @GetMapping(AvailablePaths.ADMIN_BOARDS)
    public ResponseEntity<BoardResponse> getAllBoardsAdmin(@CurrentUser UserPrincipal userPrincipal) {
        log.info("Retrieving all boards");
        User user = userDetailService.getUserFromUserPrincipal(userPrincipal);

        checkAdminPermission(user);

        return ResponseEntity.ok(BoardResponse.build(boardService.getAllBoards().stream()
                .map(BoardDto::build)
                .collect(Collectors.toList())));
    }
}
