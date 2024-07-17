package com.ericsson.trello_clone.jwt.principal;

import com.ericsson.trello_clone.domain.Board;
import com.ericsson.trello_clone.domain.User;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Data
@SuperBuilder
public class UserPrincipal implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String role;
    private boolean isExpired;
    private boolean isLocked;
    private boolean isActive;
    private Set<Board> boards;

    public static UserPrincipal buildPrincipal(User user) {
        return UserPrincipal.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .role(user.getRole())
                .isExpired(user.isExpired())
                .isLocked(user.isLocked())
                .isActive(user.isActive())
                .boards(user.getBoards())
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !this.isExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.isLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isActive();
    }

    public Set<Board> getBoards() { return this.boards; }
}
