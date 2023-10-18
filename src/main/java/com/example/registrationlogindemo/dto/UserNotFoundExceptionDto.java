package com.example.registrationlogindemo.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class UserNotFoundExceptionDto {
    private HttpStatus status;
    private String errorMessage;

    public UserNotFoundExceptionDto(HttpStatus status, String errorMessage){
        this.status=status;
        this.errorMessage =errorMessage;
    }

}
