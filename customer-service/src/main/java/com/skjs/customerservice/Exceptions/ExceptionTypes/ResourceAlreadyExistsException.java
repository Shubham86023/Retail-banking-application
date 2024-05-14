package com.skjs.customerservice.Exceptions.ExceptionTypes;

public class ResourceAlreadyExistsException extends RuntimeException {

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
}
