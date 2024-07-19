package com.ericsson.trello_clone.controller;

import com.ericsson.trello_clone.controller.helper.AvailablePaths;
import com.ericsson.trello_clone.controller.helper.CurrentUser;
import com.ericsson.trello_clone.domain.User;
import com.ericsson.trello_clone.dto.CardDto;
import com.ericsson.trello_clone.jwt.principal.UserPrincipal;
import com.ericsson.trello_clone.service.CardService;
import com.ericsson.trello_clone.service.UserDetailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
