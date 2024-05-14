package com.skjs.customerservice.Exceptions.ExceptionTypes;

public class DatabaseQueryException extends RuntimeException {

    public DatabaseQueryException(String message) {
        super(message);
    }
}
