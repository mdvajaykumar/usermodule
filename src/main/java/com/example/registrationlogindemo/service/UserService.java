package com.example.registrationlogindemo.service;

import com.example.registrationlogindemo.models.User;
import org.springframework.stereotype.Service;



public interface UserService {

    public User register(User user);

   public String login(User user);
}
