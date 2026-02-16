package com.employeeService.exceptions;

public class ResourceNofFoundException extends RuntimeException{
    public ResourceNofFoundException(String message) {
        super(message);
    }
}
