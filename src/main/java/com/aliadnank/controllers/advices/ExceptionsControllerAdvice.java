package com.aliadnank.controllers.advices;

import com.aliadnank.exceptions.InvalidTimestampException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
* @author Ali Adnan
* Advice handles the timestamp exception thrown in the services
* keeps a good separation of these concerns
* */
@ControllerAdvice
public class ExceptionsControllerAdvice {

    @ExceptionHandler(InvalidTimestampException.class)
    public ResponseEntity<String> invalidExceptionHandler(){
        return new ResponseEntity<String>("old timestamp was provided", HttpStatus.NO_CONTENT);
    }

}
