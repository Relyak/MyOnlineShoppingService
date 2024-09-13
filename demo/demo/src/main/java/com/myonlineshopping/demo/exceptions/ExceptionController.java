package com.myonlineshopping.demo.exception;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ExceptionMessage> globalExceptionHandler(GlobalException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionMessage(e.getId(), e.getMessage()));
    }

    @ExceptionHandler(AccountNotfoundException.class)
    public ResponseEntity<ExceptionMessage> accountNotfoundExceptionHandler(AccountNotfoundException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionMessage(e.getId(), e.getMessage()));
    }



}
