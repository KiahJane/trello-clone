package com.ericsson.trello_clone.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    private String usernameOrEmail;
    private String password;
}
