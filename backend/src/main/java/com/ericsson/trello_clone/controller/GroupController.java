package com.ericsson.trello_clone.controller;

import com.ericsson.trello_clone.controller.helper.AvailablePaths;
import com.ericsson.trello_clone.controller.helper.CurrentUser;
import com.ericsson.trello_clone.domain.User;
import com.ericsson.trello_clone.dto.GroupDto;
import com.ericsson.trello_clone.jwt.principal.UserPrincipal;
import com.ericsson.trello_clone.response.ApiResponse;
import com.ericsson.trello_clone.service.GroupService;
import com.ericsson.trello_clone.service.UserDetailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ericsson.trello_clone.utils.CheckPermissionUtils.*;

@Slf4j
@RestController
@AllArgsConstructor
public class GroupController {
    private GroupService groupService;
    private UserDetailService userDetailService;

    @PostMapping(AvailablePaths.NEW_GROUP)
    public ResponseEntity<GroupDto> createGroup(@CurrentUser UserPrincipal userPrincipal, @RequestBody GroupDto groupDto) {
        log.info("Creating new group from account: {}", userPrincipal.getUsername());
        User user = userDetailService.getUserFromUserPrincipal(userPrincipal);

        checkUserPermission(user);

        GroupDto result = GroupDto.build(groupService.create(groupDto));
        log.info("New group [{}] successfully created.", result.getName());

        return ResponseEntity.ok(result);
    }

    @PutMapping(AvailablePaths.GROUP)
    public ResponseEntity<GroupDto> updateGroupFromUser(@CurrentUser UserPrincipal userPrincipal, @RequestBody GroupDto groupDto) {
        log.info("Updating group [{}] from account: {}", groupDto.getName(), userPrincipal.getUsername());
        User user = userDetailService.getUserFromUserPrincipal(userPrincipal);

        checkUserPermission(user);

        GroupDto result = GroupDto.build(groupService.update(groupDto));
        log.info("Group [{}] successfully updated.", result.getName());
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(AvailablePaths.GROUP)
    public ResponseEntity<ApiResponse> deleteGroupFromUser(@CurrentUser UserPrincipal userPrincipal, @PathVariable("id") Long id) {
        log.info("Updating group from account: {}", userPrincipal.getUsername());
        User user = userDetailService.getUserFromUserPrincipal(userPrincipal);

        checkUserPermission(user);

        groupService.delete(user, id);
        log.info("Group is successfully deleted by account: {}", user.getUsername());
        return ResponseEntity.ok(new ApiResponse(Boolean.TRUE, "Successfully deleted comment."));
    }
}