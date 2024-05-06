package com.vti.demo_crud.controller;

import com.vti.demo_crud.exception.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice //ko phải dùng try/catch
public class ExceptionController {
    @ExceptionHandler(AppException.class)
    public ResponseEntity<AppException> globalExceptionHandler(AppException ex, HttpServletRequest request) {
        ex.setPath(request.getRequestURI());
        return new ResponseEntity<>(ex, HttpStatus.valueOf(ex.getStatus()));
    }
}