package com.ericsson.trello_clone.controller;

import com.ericsson.trello_clone.controller.helper.AvailablePaths;
import com.ericsson.trello_clone.controller.helper.CurrentUser;
import com.ericsson.trello_clone.domain.User;
import com.ericsson.trello_clone.dto.UserDto;
import com.ericsson.trello_clone.jwt.principal.UserPrincipal;
import com.ericsson.trello_clone.response.ApiResponse;
import com.ericsson.trello_clone.response.UserDtoResponse;
import com.ericsson.trello_clone.service.UserDetailService;
import com.ericsson.trello_clone.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ericsson.trello_clone.utils.CheckPermissionUtils.*;

@Slf4j
@RestController
@AllArgsConstructor
public class UserController {
    private UserService userService;
    private UserDetailService userDetailService;

    @GetMapping(AvailablePaths.ADMIN_USERS)
    public ResponseEntity<UserDtoResponse> getAllUsers(@CurrentUser UserPrincipal userPrincipal) {
        log.info("Getting all users, account: {}", userPrincipal.getUsername());
        User user = userDetailService.getUserFromUserPrincipal(userPrincipal);

        checkAdminPermission(user);
        UserDtoResponse userDtoResponse = new UserDtoResponse();
        return ResponseEntity.ok(userDtoResponse.users(UserDto.build(userService.getAllUsers())));
    }

    @PostMapping(AvailablePaths.ADMIN_USER)
    public ResponseEntity<ApiResponse> saveUserInformationFromAdmin(@PathVariable Long userId, @CurrentUser UserPrincipal userPrincipal, @RequestBody UserDto userDto) {
        log.info("Changing user with id [{}], account: {}", userDto.getId(), userPrincipal.getUsername());
        User user = userDetailService.getUserFromUserPrincipal(userPrincipal);

        checkAdminPermission(user);
        userService.saveUserInformationFromAdmin(userDto);

        return ResponseEntity.ok(new ApiResponse(Boolean.TRUE, "User information are saved successfully."));
    }

    @PutMapping(AvailablePaths.ADMIN_USER)
    public ResponseEntity<UserDto> updateUserAdmin(@PathVariable Long userId, @CurrentUser UserPrincipal userPrincipal, @RequestBody UserDto userDto) {
        log.info("Updating user [{}]", userId);
        User user = userDetailService.getUserFromUserPrincipal(userPrincipal);

        checkAdminPermission(user);

        return ResponseEntity.ok(UserDto.build(userDetailService.updateUser(userDto)));
    }

    @GetMapping(AvailablePaths.USER_INFO)
    public ResponseEntity<UserDto> getMyInformation(@CurrentUser UserPrincipal userPrincipal) {
        log.info("Getting information from user username: {}", userPrincipal.getUsername());
        User user = userDetailService.getUserFromUserPrincipal(userPrincipal);

        return ResponseEntity.ok(UserDto.build(userService.getMyInformation(user)));
    }
}