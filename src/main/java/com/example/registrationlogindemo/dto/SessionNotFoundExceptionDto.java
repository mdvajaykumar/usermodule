package com.example.registrationlogindemo.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class SessionNotFoundExceptionDto {

    private HttpStatus status;

    private String errorMessage;

    public SessionNotFoundExceptionDto(HttpStatus status, String message){
        this.status =status;
        this.errorMessage =message;
    }
}
