package com.boogeyman.app.controller;

import com.boogeyman.app.storage.exceptions.UserDataExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(UserDataExistException.class)
    public ResponseEntity handleSecurityError(){
        log.error("Throwing UserDataExistException");
        return ResponseEntity.unprocessableEntity().build();
    }



}
