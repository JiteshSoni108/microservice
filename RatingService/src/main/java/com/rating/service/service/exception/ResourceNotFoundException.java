package com.rating.service.service.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(){
        super("Resource ot found !");
    }
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
