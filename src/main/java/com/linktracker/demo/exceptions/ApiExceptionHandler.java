package com.linktracker.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = InvalidLinkIdException.class)
    public ResponseEntity<String> exceptionHandler(InvalidLinkIdException e){
        return new ResponseEntity<>(InvalidLinkIdException.msg, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InvalidLinkException.class)
    public ResponseEntity<ErrorMessage> exceptionHandler(InvalidLinkException e){
        return new ResponseEntity<ErrorMessage>(new ErrorMessage("Link has been invalidated", HttpStatus.UNAUTHORIZED.value()), HttpStatus.UNAUTHORIZED);
    }
}
