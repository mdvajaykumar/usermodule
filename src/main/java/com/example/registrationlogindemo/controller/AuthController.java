package com.example.registrationlogindemo.controller;


import com.example.registrationlogindemo.dto.*;
import com.example.registrationlogindemo.models.SessionStatus;
import com.example.registrationlogindemo.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private  AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto request) {
        return authService.login(request.getEmail(), request.getPassword());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody logoutRequestDto request) {
        return authService.logout(request.getToken(), request.getUserId());

    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignupRequestDto request) {
        UserDto userDto = authService.signup(request.getEmail(), request.getPassword());
        return new ResponseEntity<>(userDto, HttpStatus.OK);

    }

    @PostMapping("/validate")
    public ResponseEntity<SessionStatus> validateToken(@RequestBody ValidateTokenRequestDto request) {

        SessionStatus sessionStatus = authService.validateToken(request.getToken(), request.getUserid());
        return new ResponseEntity<>(sessionStatus, HttpStatus.OK);

    }


}
