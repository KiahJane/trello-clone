package com.ericsson.trello_clone.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EntityFoundInDatabaseException extends CustomException {
    public EntityFoundInDatabaseException(String message) {
        super(message);
    }
}

