package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.models.User;
import com.example.registrationlogindemo.service.UserServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post/users")
public class UserController {

//    private UserServiceImpl userService;
//
//    public UserController(UserServiceImpl userService) {
//        this.userService = userService;
//    }
//
//
//    @PostMapping
//    public User registration(@RequestBody User user) {
//        return  userService.register(user);
//
//    }
//
//
//    @PostMapping("/login")
//    public String login(@RequestBody User user) {
//        return  userService.login(user);
//
//    }


}
