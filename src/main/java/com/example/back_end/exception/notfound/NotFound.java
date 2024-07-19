package com.example.back_end.exception.notfound;

public class NotFound extends RuntimeException{
    public NotFound()
    {
        super("Resource Not Found");
    }
}
