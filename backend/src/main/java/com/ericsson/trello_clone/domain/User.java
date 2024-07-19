package com.ericsson.trello_clone.domain;

import com.ericsson.trello_clone.config.ApplicationRoles;
import com.ericsson.trello_clone.dto.BoardDto;
import com.ericsson.trello_clone.dto.UserDto;
import com.ericsson.trello_clone.request.RegisterRequest;
import com.ericsson.trello_clone.utils.StringUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "app_user")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "role")
    private String role;

    @Column(name = "approved_by_admin")
    private Boolean approvedByAdmin;

    @Column(name = "is_expired", nullable = false)
    private boolean isExpired;

    @Column(name = "is_locked", nullable = false)
    private boolean isLocked;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @ManyToMany
    @JoinTable(
            name = "user_boards",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "board_id")
    )
    private Set<Board> boards;

    public static User buildUserForCreate(RegisterRequest registerRequest) {
        return User.builder()
                .id(null)
                .username(registerRequest.getUsername())
                .password(registerRequest.getPassword())
                .email(StringUtils.allLowercase(registerRequest.getEmail()))
                .role(ApplicationRoles.UNAPPROVED_USER.getDatabaseName())
                .approvedByAdmin(false)
                .isExpired(false)
                .isLocked(false)
                .isActive(true)
                .boards(null)
                .build();
    }

    public void updateEntity(UserDto userDto) {
        this.username = userDto.getUsername();
        this.password = userDto.getPassword();
        this.email = userDto.getEmail();
        this.role = userDto.getRole();
        this.approvedByAdmin = userDto.getApprovedByAdmin();
        this.isExpired = userDto.isExpired();
        this.isLocked = userDto.isLocked();
        this.isActive = userDto.isActive();
        this.boards = userDto.getBoards().stream().map(BoardDto::toEntity).collect(Collectors.toSet());
    }
}
