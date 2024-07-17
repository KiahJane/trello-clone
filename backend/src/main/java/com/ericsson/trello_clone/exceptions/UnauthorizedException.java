package com.ericsson.trello_clone.exceptions;

public class UnauthorizedException extends CustomException {

    public UnauthorizedException() {
        super("User is not authorized to create a board.");
    }
}
