package com.example.back_end.exception.notfound;

import com.example.back_end.exception.allreadyexists.AllReadyExists;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class NotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(NotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,String> exceptionHandler(NotFound exception){

        Map<String,String> errorMap=new HashMap<>();
        errorMap.put("message",exception.getMessage());
        errorMap.put("status", String.valueOf(HttpStatus.CONFLICT.value()));
        return errorMap;
    }
}
