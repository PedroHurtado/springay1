package com.example.demo.core;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class NotFoundExceptionHandler {    
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handler(NotFoundException ex){
        //logger
        return ResponseEntity.notFound().build();
    }
}
