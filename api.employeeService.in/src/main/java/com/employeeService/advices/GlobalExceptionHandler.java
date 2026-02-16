package com.employeeService.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiError> handleResourceNotFound(NoSuchElementException exception){
        ApiError apiError=ApiError.builder().message("Resource not found").status(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
    }
}
