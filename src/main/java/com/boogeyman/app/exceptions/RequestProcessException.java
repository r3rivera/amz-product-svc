package com.boogeyman.app.exceptions;

public class RequestProcessException extends RuntimeException{

    public RequestProcessException(String message) {
        super(message);
    }

    public RequestProcessException(String message, Throwable cause) {
        super(message, cause);
    }
}
