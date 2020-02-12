package com.rbs.Exception;

import com.rbs.model.RestApiStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TransactionExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { AccountNotFoundException.class, InsufficientFundException.class })
    protected ResponseEntity<Object> handleConflict(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new RestApiStatus(HttpStatus.FORBIDDEN, "300", "Failed",ex.getMessage()),HttpStatus.FORBIDDEN);
    }
}
