package com.ericsson.trello_clone.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApplicationRoles {
    ADMIN("Admin"),
    USER("User"),
    UNAPPROVED_USER("Unapproved");

    private final String databaseName;
}
