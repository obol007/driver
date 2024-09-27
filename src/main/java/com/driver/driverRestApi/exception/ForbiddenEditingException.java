package com.driver.driverRestApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenEditingException extends RuntimeException {

    public ForbiddenEditingException (String message){
        super(message);
    }

}
