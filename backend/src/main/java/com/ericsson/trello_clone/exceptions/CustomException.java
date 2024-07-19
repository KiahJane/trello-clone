package com.ericsson.trello_clone.exceptions;

import com.ericsson.trello_clone.response.ExceptionResponse;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
@Getter
public class CustomException extends RuntimeException {
    private ExceptionResponse exceptionResponse;

    public CustomException(String message) {
        super(message);
        log.error(message);
        setUpResponse(message);
    }

    private void setUpResponse(String message) {
        exceptionResponse = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }
}
