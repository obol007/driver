package com.driver.driverRestApi.exception.advice;

import com.driver.driverRestApi.exception.NoContentException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NoContent {


    @ResponseBody
    @ExceptionHandler(NoContentException.class)
    @ResponseStatus(HttpStatus.OK)
    String noContentHandler(NoContentException ex){
        return ex.getMessage();
    }
}
