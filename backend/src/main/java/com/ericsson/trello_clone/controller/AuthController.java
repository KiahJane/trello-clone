package com.ericsson.trello_clone.controller;

import com.ericsson.trello_clone.controller.helper.AvailablePaths;
import com.ericsson.trello_clone.domain.User;
import com.ericsson.trello_clone.dto.UserDto;
import com.ericsson.trello_clone.exceptions.AnotherEntityFoundException;
import com.ericsson.trello_clone.exceptions.EntityNotFoundInDatabaseException;
import com.ericsson.trello_clone.exceptions.PasswordChangeException;
import com.ericsson.trello_clone.exceptions.SimpleAuthenticationException;
import com.ericsson.trello_clone.jwt.JwtTokenProvider;
import com.ericsson.trello_clone.request.ChangePasswordRequest;
import com.ericsson.trello_clone.request.LoginRequest;
import com.ericsson.trello_clone.request.RegisterRequest;
import com.ericsson.trello_clone.response.JwtAuthenticationResponse;
import com.ericsson.trello_clone.service.UserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailService service;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    public AuthController(AuthenticationManager authenticationManager, UserDetailService service, PasswordEncoder passwordEncoder, JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.service = service;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping(AvailablePaths.AUTH_LOGIN)
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {
            String jwt = getJwt(loginRequest.getUsernameOrEmail(), loginRequest.getPassword());

            JwtAuthenticationResponse jwtResponse = JwtAuthenticationResponse.getFromUser(service.getUserById(tokenProvider.getUserIdFromJWT(jwt)), jwt);

            return ResponseEntity.ok(jwtResponse);
        } catch (RuntimeException e) {
            throw new SimpleAuthenticationException();
        }
    }

    @CrossOrigin
    @PostMapping(AvailablePaths.AUTH_REGISTER)
    public ResponseEntity<JwtAuthenticationResponse> registerUser(@RequestBody RegisterRequest registerRequest) {
        if (service.userExistsByUsername(registerRequest.getUsername())) {
            throw new AnotherEntityFoundException("Odabrano korisničko ime je zauzeto.");
        }
        if (service.userExistsByEmail(registerRequest.getEmail())) {
            throw new AnotherEntityFoundException("Odabrana email adresa je zauzeta.");
        }
        User user = User.buildUserForCreate(registerRequest);
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        user = service.createUser(user);

        try {
            String jwt = getJwt(user.getUsername(), password);
            JwtAuthenticationResponse jwtResponse = JwtAuthenticationResponse.getFromUser(user, jwt);

            return ResponseEntity.ok(jwtResponse);
        } catch (RuntimeException e) {
            throw new SimpleAuthenticationException();
        }
    }

    @PostMapping(AvailablePaths.CHANGE_PASSWORD)
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        try {
            User user = service.getUserByEmail(changePasswordRequest.getEmail());
            if (user == null) {
                throw new EntityNotFoundInDatabaseException("User not found");
            }

            user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
            UserDto userDto = UserDto.build(user);
            service.updateUser(userDto);

            return ResponseEntity.ok("Password updated successfully");
        } catch (RuntimeException e) {
            throw new PasswordChangeException();
        }
    }

    private String getJwt(String username, String password) {
        Authentication newAuthentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(newAuthentication);

        return tokenProvider.generateToken(newAuthentication);
    }
}
