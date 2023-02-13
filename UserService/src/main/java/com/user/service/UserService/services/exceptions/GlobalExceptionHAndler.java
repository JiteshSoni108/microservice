package com.user.service.UserService.services.exceptions;

import com.user.service.UserService.services.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHAndler {

    @ExceptionHandler(ResourceNotFoundExceptions.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundExeption(ResourceNotFoundExceptions ex){
        return new ResponseEntity<ApiResponse>(ApiResponse.builder().message(ex.getMessage()).success(true).status(HttpStatus.NOT_FOUND).build(), HttpStatus.NOT_FOUND);
    }
}
