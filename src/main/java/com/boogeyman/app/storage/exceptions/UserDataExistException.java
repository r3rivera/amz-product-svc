package com.boogeyman.app.storage.exceptions;

public class UserDataExistException extends RuntimeException{
    private static final String USER_ALREADY_EXIST = "Already Exist";
    public UserDataExistException() {
        this(USER_ALREADY_EXIST);
    }

    public UserDataExistException(String message) {
        super(message);
    }

    public UserDataExistException(String message, Throwable cause) {
        super(USER_ALREADY_EXIST, cause);
    }

    public UserDataExistException(Throwable cause) {
        super(cause);
    }

    public UserDataExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(USER_ALREADY_EXIST, cause, enableSuppression, writableStackTrace);
    }
}
