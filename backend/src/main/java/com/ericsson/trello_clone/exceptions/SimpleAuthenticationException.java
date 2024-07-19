package com.ericsson.trello_clone.exceptions;

public class SimpleAuthenticationException extends CustomException {

    public SimpleAuthenticationException() {
        super("Problem with login.");
    }
}
