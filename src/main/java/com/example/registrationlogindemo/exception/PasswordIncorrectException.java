package com.example.registrationlogindemo.exception;

import org.springframework.http.HttpStatus;

public class PasswordIncorrectException extends  RuntimeException{

    public PasswordIncorrectException( String  message){
        super(message);
    }
}
