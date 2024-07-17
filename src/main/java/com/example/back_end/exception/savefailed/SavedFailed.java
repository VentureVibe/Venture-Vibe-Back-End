package com.example.back_end.exception.savefailed;


public class SavedFailed extends RuntimeException{
    public SavedFailed()
    {
        super("Saving Failed ");
    }
}
