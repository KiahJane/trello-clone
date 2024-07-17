package com.ericsson.trello_clone.controller;

import com.ericsson.trello_clone.config.ApplicationRoles;
import com.ericsson.trello_clone.controller.helper.AvailablePaths;
import com.ericsson.trello_clone.controller.helper.CurrentUser;
import com.ericsson.trello_clone.domain.User;
import com.ericsson.trello_clone.dto.UserDto;
import com.ericsson.trello_clone.exceptions.PermissionException;
import com.ericsson.trello_clone.jwt.principal.UserPrincipal;
import com.ericsson.trello_clone.response.ApiResponse;
import com.ericsson.trello_clone.response.StringResponse;
import com.ericsson.trello_clone.response.UserDtoResponse;
import com.ericsson.trello_clone.service.UserDetailService;
import com.ericsson.trello_clone.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class UserController {
    private UserService userService;
    private UserDetailService userDetailService;

    private static void checkPermission(User user) {
        if (!user.getRole().equals(ApplicationRoles.ADMIN.getDatabaseName())) {
            throw new PermissionException();
        }
    }

    @GetMapping(AvailablePaths.ADMIN_LIST_OF_ALL_USERS)
    public ResponseEntity<UserDtoResponse> getAllUsersFromAdmin(@CurrentUser UserPrincipal userPrincipal) {
        log.info("Getting all users, account: {}", userPrincipal.getUsername());
        User user = userDetailService.getUserFromUserPrincipal(userPrincipal);

        checkPermission(user);
        UserDtoResponse userDtoResponse = new UserDtoResponse();
        return ResponseEntity.ok(userDtoResponse.users(UserDto.build(userService.getAllUsers())));
    }

    @PostMapping(AvailablePaths.ADMIN_LIST_OF_ALL_USERS)
    public ResponseEntity<ApiResponse> saveUserInformationFromAdmin(@CurrentUser UserPrincipal userPrincipal, @RequestBody UserDto userDto) {
        log.info("Changing user with id [{}], account: {}", userDto.getId(), userPrincipal.getUsername());
        User user = userDetailService.getUserFromUserPrincipal(userPrincipal);

        checkPermission(user);
        userService.saveUserInformationFromAdmin(userDto);

        return ResponseEntity.ok(new ApiResponse(Boolean.TRUE, "User information are saved successfully."));
    }

    @GetMapping(AvailablePaths.ADMIN_ROLES)
    public ResponseEntity<StringResponse> getAllUserRolesFromAdmin(@CurrentUser UserPrincipal userPrincipal) {
        log.info("Getting all users, account: {}", userPrincipal.getUsername());
        User user = userDetailService.getUserFromUserPrincipal(userPrincipal);

        checkPermission(user);

        return ResponseEntity.ok(StringResponse.build(userService.getAllRoles()));
    }

}
