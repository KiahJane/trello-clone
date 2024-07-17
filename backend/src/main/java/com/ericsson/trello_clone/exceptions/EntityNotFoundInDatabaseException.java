package com.ericsson.trello_clone.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EntityNotFoundInDatabaseException extends CustomException {
    public EntityNotFoundInDatabaseException(String message) {
        super(message);
    }

    /**
     * Constructor for specifying id.
     *
     * @param id of entity that is not found.
     */
    public EntityNotFoundInDatabaseException(String className, Long id) {
        super(getMessage(className, id));
    }

    private static String getMessage(String className, Long id) {
        return String.format("Entity of [%s] with id %d not found.", className, id);
    }
}

