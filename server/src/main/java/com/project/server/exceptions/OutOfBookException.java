package com.project.server.exceptions;

public class OutOfBookException extends RuntimeException {
    public OutOfBookException(String message) {
        super(message);
    }
}
