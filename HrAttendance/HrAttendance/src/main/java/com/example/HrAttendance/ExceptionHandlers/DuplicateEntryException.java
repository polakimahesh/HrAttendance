package com.example.HrAttendance.ExceptionHandlers;

public class DuplicateEntryException extends RuntimeException{
    private String errorCode;

    public DuplicateEntryException(String errorCode,String message){
        super(message);
        this.errorCode=errorCode;
    }
    public String getErrorCode(){
        return errorCode;
    }
}
