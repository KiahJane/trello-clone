package com.ericsson.trello_clone.exceptions;

public class PasswordChangeException extends CustomException{

    public PasswordChangeException() {
        super("Failed to change password.");
    }
}
