package com.ericsson.trello_clone.response;

import com.ericsson.trello_clone.domain.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class JwtAuthenticationResponse {

    @JsonProperty("accessToken")
    private String accessToken;
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("role")
    private String role;

    public static JwtAuthenticationResponse getFromUser(User user, String jwt) {
        return JwtAuthenticationResponse.builder()
                .accessToken(jwt)
                .id(user.getId())
                .name(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
