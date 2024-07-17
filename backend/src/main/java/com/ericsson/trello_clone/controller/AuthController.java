package com.ericsson.trello_clone.controller;

import com.ericsson.trello_clone.controller.helper.AvailablePaths;
import com.ericsson.trello_clone.domain.User;
import com.ericsson.trello_clone.exceptions.AnotherEntityFoundException;
import com.ericsson.trello_clone.exceptions.SimpleAuthenticationException;
import com.ericsson.trello_clone.jwt.JwtTokenProvider;
import com.ericsson.trello_clone.request.LoginRequest;
import com.ericsson.trello_clone.request.SignUpRequest;
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

    @PostMapping(AvailablePaths.AUTH_LOG_IN)
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
    @PostMapping(AvailablePaths.AUTH_SIGN_IN)
    public ResponseEntity<JwtAuthenticationResponse> registerUser(@RequestBody SignUpRequest signUpRequest) {
        if (service.userExistsByUsername(signUpRequest.getUsername())) {
            throw new AnotherEntityFoundException("Odabrano korisničko ime je zauzeto.");
        }
        if (service.userExistsByEmail(signUpRequest.getEmail())) {
            throw new AnotherEntityFoundException("Odabrana email adresa je zauzeta.");
        }
        User user = User.buildUserForCreate(signUpRequest);
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

    private String getJwt(String username, String password) {
        Authentication newAuthentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(newAuthentication);

        return tokenProvider.generateToken(newAuthentication);
    }
}
