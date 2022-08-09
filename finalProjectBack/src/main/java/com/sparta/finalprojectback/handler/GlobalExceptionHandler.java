package com.sparta.finalprojectback.handler;


import com.sparta.finalprojectback.community.controller.CommunityController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(value = Exception.class)
    public String handleException(Exception e){
        logger.error("error", e);
        return e.getMessage();
    }

    // validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleValidationExceptions(MethodArgumentNotValidException ex) {
        String error;
        error = ex.getBindingResult().getFieldError().getDefaultMessage();
        return error;
    }

}
