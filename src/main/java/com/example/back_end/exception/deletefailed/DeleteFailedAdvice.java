package com.example.back_end.exception.deletefailed;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.HashMap;
import java.util.Map;

public class DeleteFailedAdvice {
    @ResponseBody
    @ExceptionHandler(DeleteFailed.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String,String> exceptionHandler(DeleteFailed exception){

        Map<String,String> errorMap=new HashMap<>();
        errorMap.put("message",exception.getMessage());
        errorMap.put("status", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        return errorMap;
    }
}
