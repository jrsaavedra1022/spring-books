package com.asgeek.books.web.exceptions;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class GenericResponseException extends RuntimeException implements Serializable{
    // Attributos
    private final transient Object errorResponse;
    private final HttpStatus httpStatus;
    private final boolean isObj;

    // Métodos de la clase
    public GenericResponseException(Object errorResponse, HttpStatus httpStatus){
        this.errorResponse = errorResponse;
        this.httpStatus = httpStatus;
        this.isObj = true;
    }

    public  GenericResponseException(String message, HttpStatus httpStatus){
        super(message);
        this.errorResponse = null;
        this.httpStatus = httpStatus;
        this.isObj = false;
    }

    public Object getErrorResponse() {
        return errorResponse;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public boolean isObj() {
        return isObj;
    }
}
