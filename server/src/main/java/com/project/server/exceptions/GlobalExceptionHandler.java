package com.project.server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * The global exception handler for every exceptions in the service layers
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle the exceptions of not founding the record
     * in the database
     * @param ex
     * @return the error response with the exception message and the HTTP status Not Found
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RecordNotFoundException.class)
    public ErrorResponse recordNotFoundHandler(RecordNotFoundException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    /**
     * Handle the exceptions of out-of-quantity books
     * in the database
     * @param ex
     * @return the error response with the exception message and the HTTP status Bad Request
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OutOfBookException.class)
    public ErrorResponse outOfBookHandler(OutOfBookException ex) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }
}

