package com.driver.driverRestApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmptyTagException extends RuntimeException{

    public EmptyTagException(String message) {
        super(message);
    }
}
