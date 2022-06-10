package com.malic.musker.exception;

import org.springframework.stereotype.Component;

@Component
public class CustomException extends RuntimeException{
    public CustomException(){
        super();
    }
}
