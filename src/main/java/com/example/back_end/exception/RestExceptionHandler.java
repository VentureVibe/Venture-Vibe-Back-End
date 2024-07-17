package com.example.Crisp.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.NoSuchElementException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice()
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
//    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
//    public ResponseEntity<Object> handleSqlIntegrityException(HttpServletRequest req,SQLIntegrityConstraintViolationException ex){
//
//        String error = "Unable to submit post: " + ex.getMessage();
//        return buildResponseEntity(new ErrorResponse(HttpStatus.BAD_REQUEST, error));
//    }
//
//    private ResponseEntity<Object> buildResponseEntity(ErrorResponse errorResponse){
//        return new ResponseEntity<Object>(errorResponse, errorResponse.getStatus());
//    }

}
