package io.nology.to_dos.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.nology.to_dos.common.exceptions.NotFoundException;

//give some more tips to your controllers
//do this stuff no matter what
//handle the exception - incase we have a notfound exception handler


@ControllerAdvice 

public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {

        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }



}
