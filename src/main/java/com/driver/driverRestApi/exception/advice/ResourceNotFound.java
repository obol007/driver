package com.driver.driverRestApi.exception.advice;
import com.driver.driverRestApi.exception.EmptyTagException;
import com.driver.driverRestApi.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ResourceNotFound {


    @ResponseBody
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String resourceNotFoundHandler(ResourceNotFoundException ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(EmptyTagException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    String emptyTagHandler(EmptyTagException ex){
        return ex.getMessage();
    }
}


