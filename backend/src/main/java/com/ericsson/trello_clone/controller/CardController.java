package com.ericsson.trello_clone.controller;

import com.ericsson.trello_clone.controller.helper.AvailablePaths;
import com.ericsson.trello_clone.controller.helper.CurrentUser;
import com.ericsson.trello_clone.domain.User;
import com.ericsson.trello_clone.dto.CardDto;
import com.ericsson.trello_clone.jwt.principal.UserPrincipal;
import com.ericsson.trello_clone.response.ApiResponse;
import com.ericsson.trello_clone.service.CardService;
import com.ericsson.trello_clone.service.UserDetailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ericsson.trello_clone.utils.CheckPermissionUtils.checkUserPermission;

@Slf4j
@RestController
@AllArgsConstructor
public class CardController {
    private CardService cardService;
    private UserDetailService userDetailService;

    @PostMapping(AvailablePaths.NEW_CARD)
    public ResponseEntity<CardDto> createCard(@CurrentUser UserPrincipal userPrincipal, @RequestBody CardDto cardDto) {
        log.info("Creating new group from account: {}", userPrincipal.getUsername());
        User user = userDetailService.getUserFromUserPrincipal(userPrincipal);

        checkUserPermission(user);

        CardDto result = CardDto.build(cardService.create(cardDto));
        log.info("New card successfully created: {}", result.getText());

        return ResponseEntity.ok(result);
    }

    @PutMapping(AvailablePaths.CARD)
    public ResponseEntity<CardDto> updateCard(@PathVariable Long boardId, @PathVariable Long groupId, @PathVariable Long cardId, @CurrentUser UserPrincipal userPrincipal, @RequestBody CardDto cardDto) {
        log.info("Updating card [{}]", cardId);
        User user = userDetailService.getUserFromUserPrincipal(userPrincipal);

        checkUserPermission(user);

        CardDto result = CardDto.build(cardService.update(cardDto));
        log.info("New card successfully updated: {}", result.getText());

        return ResponseEntity.ok(result);
    }

    @DeleteMapping(AvailablePaths.CARD)
    public ResponseEntity<ApiResponse> deleteCard(@PathVariable Long boardId, @PathVariable Long groupId, @PathVariable Long cardId, @CurrentUser UserPrincipal userPrincipal) {
        log.info("Deleting card [{}]", cardId);
        User user = userDetailService.getUserFromUserPrincipal(userPrincipal);

        checkUserPermission(user);

        cardService.delete(cardId);
        log.info("Card [{}] successfully deleted.", cardId);
        return ResponseEntity.ok(new ApiResponse(Boolean.TRUE, "Successfully deleted card."));
    }
}
