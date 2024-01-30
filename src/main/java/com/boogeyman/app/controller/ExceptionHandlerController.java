package com.boogeyman.app.controller;

import com.boogeyman.app.exceptions.RequestProcessException;
import com.boogeyman.app.model.AppError;
import com.boogeyman.app.model.ResponseError;
import com.boogeyman.app.storage.exceptions.DataStoreException;
import com.boogeyman.app.storage.exceptions.UserDataExistException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

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

    @ExceptionHandler(RequestProcessException.class)
    public ResponseEntity handleRequestProcessError(RequestProcessException ex){
        log.error("Throwing RequestProcessException");
        return ResponseEntity.unprocessableEntity().body(ex.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        String requestURI = "";
        if(request instanceof ServletWebRequest){
            final HttpServletRequest servletRequest = ((ServletWebRequest)request).getRequest();
            requestURI = servletRequest.getRequestURI();
        }
        final List<AppError> errorResponses = new ArrayList<>();
        final List<FieldError> errors = ex.getFieldErrors();
        if(errors.isEmpty()) {
            final BindingResult bindingResult = ex.getBindingResult();
            final List<ObjectError> objErrors = bindingResult.getAllErrors();
            objErrors.forEach(i -> {
                    final AppError error = new AppError();
                    error.setType(i.getObjectName());
                    error.setMessage(i.getDefaultMessage());
                    errorResponses.add(error);
            });
        }
        errors.forEach(i -> {
            final AppError error = new AppError();
            error.setPath(i.getField());
            error.setInvalidValue(i.getRejectedValue());
            error.setMessage(i.getDefaultMessage());
            error.setType(i.getObjectName());
            errorResponses.add(error);
        });

        final ResponseError error = new ResponseError(HttpStatus.BAD_REQUEST, errorResponses, requestURI);
        return ResponseEntity.badRequest().body(error);
    }



}
