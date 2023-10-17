package com.example.registrationlogindemo.service;


import com.example.registrationlogindemo.dto.UserDto;
import com.example.registrationlogindemo.models.SessionStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
     ResponseEntity<UserDto> login(String email, String password);

     UserDto signup(String email, String password);

     ResponseEntity<Void> logout(String token, long userId);

    SessionStatus validateToken(String token, long userId);


}
