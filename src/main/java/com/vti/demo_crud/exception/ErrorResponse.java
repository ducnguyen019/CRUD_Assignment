package com.vti.demo_crud.exception;

public enum ErrorResponse {
    NOT_FOUND (404, "DATA NOT FOUND"), EXISTED (500, "GROUPNAME EXISTED");
    public final int status;
    public final String message;
    ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus(){
        return status;
    }

    public String getMessage(){
        return message;
    }
}
