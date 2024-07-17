package com.example.back_end.exception.allreadyexists;

public class AllReadyExists extends RuntimeException{
    public AllReadyExists(String id)
    {
        super("Already exists entity from this data "+id);
    }
}
