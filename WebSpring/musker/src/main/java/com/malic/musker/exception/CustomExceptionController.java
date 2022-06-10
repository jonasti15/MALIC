package com.malic.musker.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionController{

    @ExceptionHandler(CustomException.class)
    public String tokenExpired(CustomException e){
        return "redirect:/logout";
    }
}
