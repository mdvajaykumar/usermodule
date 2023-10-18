package com.example.registrationlogindemo.exception;

public class SessionNotFoundException extends  RuntimeException{

    public SessionNotFoundException(String message){
        super(message);
    }
}
