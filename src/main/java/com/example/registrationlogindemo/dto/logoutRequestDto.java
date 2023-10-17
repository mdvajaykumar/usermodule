package com.example.registrationlogindemo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class logoutRequestDto {
    private String token;
    private long userId;
}
