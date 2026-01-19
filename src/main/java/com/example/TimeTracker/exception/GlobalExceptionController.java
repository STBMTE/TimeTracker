package com.example.TimeTracker.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {
    @ExceptionHandler({OverlapException.class, IllegalArgumentException.class})
    public ResponseEntity<String> handle(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
