package com.ericsson.trello_clone.response;

import com.ericsson.trello_clone.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
public class UserDtoResponse {
    @JsonProperty("users")
    private List<UserDto> users;

    public UserDtoResponse users(List<UserDto> users) {
        if (this.users == null) {
            this.users = new ArrayList<>();
        }
        this.users.addAll(users);
        return this;
    }
}
