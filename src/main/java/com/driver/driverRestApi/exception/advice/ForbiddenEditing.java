package com.driver.driverRestApi.exception.advice;

import com.driver.driverRestApi.exception.ForbiddenEditingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ForbiddenEditing {

    @ResponseBody
    @ExceptionHandler(ForbiddenEditingException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String forbiddenEditingHandler(ForbiddenEditingException ex){
        return ex.getMessage();
    }
}

