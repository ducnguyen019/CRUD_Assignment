package com.vti.demo_crud.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties({"stackTrace", "cause", "suppressed", "localizedMessage"})
public class AppException extends RuntimeException{
    private Date timestamp;
    private int status;
    private String message;
    private String path;

    public AppException(){

    }

    public AppException(ErrorResponse errorResponse){
        this.timestamp = new Date();
        this.status = errorResponse.getStatus();
        this.message = errorResponse.getMessage();
    }
}
