package com.example.back_end.exception.savefailed;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class SavedFailedAdvice {

    @ResponseBody
    @ExceptionHandler(SavedFailed.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleSavedFailed(SavedFailed exception) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", exception.getMessage());
        errorMap.put("status", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        return errorMap;
    }
}
