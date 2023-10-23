package com.example.registrationlogindemo.service;

import com.example.registrationlogindemo.dto.UserDto;
import com.example.registrationlogindemo.models.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

//    public User register(User user);
//   public String login(User user);

    public User getUserDetails(Long userId);
    public UserDto setUserDetails (Long userId, List<Long> roleId);
}
