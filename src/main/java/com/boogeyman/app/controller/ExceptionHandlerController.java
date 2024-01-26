package com.boogeyman.app.controller;

import com.boogeyman.app.storage.exceptions.DataStoreException;
import com.boogeyman.app.storage.exceptions.UserDataExistException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(UserDataExistException.class)
    public ResponseEntity handleSecurityError(){
        log.error("Throwing UserDataExistException");
        return ResponseEntity.unprocessableEntity().build();
    }


    @ExceptionHandler(DataStoreException.class)
    public ResponseEntity handleDataError(){
        log.error("Throwing DataStoreException");
        return ResponseEntity.unprocessableEntity().build();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity validationError(HttpServletRequest request, MethodArgumentNotValidException ex){
        log.error("Request field validation error!");
        log.error(request.getPathInfo());
        final FieldError field = ex.getFieldError();
        final String value = String.valueOf(field.getRejectedValue());
        final String path = request.getRequestURI();

        return ResponseEntity.badRequest().build();
    }

}
