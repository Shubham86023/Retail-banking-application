package com.skjs.customerservice.Exceptions.ExceptionTypes;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
