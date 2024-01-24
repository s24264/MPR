package com.example.MPR;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;
import java.util.Optional;
@ControllerAdvice
public class DogExceptionHandler extends ResponseEntityExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest request){
        return ResponseEntity.notFound().build();
    }
}
