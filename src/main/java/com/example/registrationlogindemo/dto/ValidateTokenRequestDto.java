package com.example.registrationlogindemo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateTokenRequestDto {
    private long userid;

    private String token;
}
