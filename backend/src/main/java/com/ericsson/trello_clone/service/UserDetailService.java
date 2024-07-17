package com.ericsson.trello_clone.service;

import com.ericsson.trello_clone.domain.User;
import com.ericsson.trello_clone.dto.UserDto;
import com.ericsson.trello_clone.jwt.principal.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserDetailService extends UserDetailsService {

    User getUserById(Long id);

    boolean userExistsByUsername(String username);

    boolean userExistsByEmail(String email);

    User createUser(User user);

    UserDetails loadUserById(Long id);

    User getUserFromUserPrincipal(UserPrincipal userPrincipal);

    User getUserFromUserDto(UserDto userDto);
}
