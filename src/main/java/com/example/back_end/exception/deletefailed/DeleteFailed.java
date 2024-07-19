package com.example.back_end.exception.deletefailed;


public class DeleteFailed extends RuntimeException{
    public DeleteFailed ()
    {
        super("Deletion Failed");
    }
}