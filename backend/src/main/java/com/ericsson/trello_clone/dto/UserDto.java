package com.ericsson.trello_clone.dto;

import com.ericsson.trello_clone.domain.User;
import com.ericsson.trello_clone.utils.StringUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@SuperBuilder
@Slf4j
public class UserDto {
    @JsonProperty("id")
    private Long id;

    @NotBlank(message = "Username is mandatory")
    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @JsonProperty("email")
    private String email;

    @JsonProperty("role")
    private String role;

    @JsonProperty("approvedByAdmin")
    private Boolean approvedByAdmin;

    @JsonProperty("isExpired")
    private boolean isExpired;

    @JsonProperty("isLocked")
    private boolean isLocked;

    @JsonProperty("isActive")
    private boolean isActive;

    @JsonProperty("boards")
    private Set<BoardDto> boards;

    public static UserDto build(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(StringUtils.allLowercase(user.getEmail()))
                .role(user.getRole())
                .approvedByAdmin(user.getApprovedByAdmin())
                .isExpired(user.isExpired())
                .isLocked(user.isLocked())
                .isActive(user.isActive())
                .boards(user.getBoards().stream().map(BoardDto::build).collect(Collectors.toSet()))
                .build();
    }

    public static List<UserDto> build(List<User> user) {
        return user.stream().map(UserDto::build).collect(Collectors.toList());
    }

    public User toEntity() {
        return User.builder()
                .id(this.id)
                .username(this.username)
                .password(this.password)
                .email(this.email)
                .role(this.role)
                .approvedByAdmin(this.approvedByAdmin)
                .isExpired(this.isExpired)
                .isLocked(this.isLocked)
                .isActive(this.isActive)
                .boards(this.boards.stream().map(BoardDto::toEntity).collect(Collectors.toSet()))
                .build();
    }
}
