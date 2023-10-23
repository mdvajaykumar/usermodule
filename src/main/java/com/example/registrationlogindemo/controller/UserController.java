package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.dto.SetUserRoleRequestDto;
import com.example.registrationlogindemo.dto.UserDto;
import com.example.registrationlogindemo.models.User;
import com.example.registrationlogindemo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private  UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUserDetails(@PathVariable("id") long userId) {
        User user = userService.getUserDetails(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<UserDto> setUserRoles(@PathVariable("id") Long userId, @RequestBody SetUserRoleRequestDto request) {
        UserDto userDto = userService.setUserDetails(userId, request.getUserIds());
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }


}
