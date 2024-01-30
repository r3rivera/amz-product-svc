package com.boogeyman.app.model;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ResponseError {

    private HttpStatus status;
    private String requestPath;
    private LocalDateTime dateTime;
    private List<AppError> errors;

    public ResponseError(HttpStatus status, List<AppError> errors, String requestPath){
        this.status = status;
        this.errors = errors;
        this.requestPath = requestPath;
        this.dateTime = LocalDateTime.now();
    }

}
