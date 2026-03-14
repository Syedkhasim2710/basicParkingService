package com.r360.exception;

import com.r360.controller.ParkingController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = ParkingController.class)
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handle(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
