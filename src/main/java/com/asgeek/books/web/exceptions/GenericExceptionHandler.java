package com.asgeek.books.web.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(GenericResponseException.class)
    public ResponseEntity<Object> customExceptionHandler(GenericResponseException exception){
        if(exception.isObj())
            return new ResponseEntity<>(exception.getErrorResponse(), exception.getHttpStatus());

        // Mensaje personalizado
        Map<String, String> errors = new HashMap<>();
        errors.put("error", exception.getMessage());

        return new ResponseEntity<>(errors, exception.getHttpStatus());
    }

}
