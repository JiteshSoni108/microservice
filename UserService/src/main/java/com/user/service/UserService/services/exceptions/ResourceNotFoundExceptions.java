package com.user.service.UserService.services.exceptions;

public class ResourceNotFoundExceptions extends RuntimeException{

    public ResourceNotFoundExceptions(){
        super("Resource not found on server !");
    }

    public ResourceNotFoundExceptions(final String message){
        super(message);
    }
}
