package com.example.back_end.exception.allreadyexists;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AllReadyExistsAdvice {
    @ResponseBody
    @ExceptionHandler(AllReadyExists.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String,String> exceptionHandler(AllReadyExists exception){

        Map<String,String> errorMap=new HashMap<>();
        errorMap.put("errorMessage",exception.getMessage());
        errorMap.put("status", String.valueOf(HttpStatus.CONFLICT.value()));
        return errorMap;
    }
}
