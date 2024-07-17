package com.ericsson.trello_clone.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AnotherEntityFoundException extends CustomException {
    public AnotherEntityFoundException(String message) {
        super(message);
    }
}
