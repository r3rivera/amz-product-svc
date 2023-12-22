package com.boogeyman.app.storage.exceptions;

public class DataStoreException extends RuntimeException{

    public DataStoreException(String message) {
        super(message);
    }

    public DataStoreException(String message, Throwable cause) {
        super(message, cause);
    }
}
