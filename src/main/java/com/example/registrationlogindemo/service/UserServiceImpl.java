package com.example.registrationlogindemo.service;

import com.example.registrationlogindemo.models.Session;
import com.example.registrationlogindemo.models.User;
import com.example.registrationlogindemo.repositories.SessionRepository;
import com.example.registrationlogindemo.repositories.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private  UserRepository userRepository;

    private SessionRepository sessionRepository;

    public UserServiceImpl(UserRepository userRepository ,SessionRepository sessionRepository) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public User register(User user) {
        return userRepository.save(user);
    }

    @Override
    public String login(User user) {
        User user1 = userRepository.findByEmail(user.getEmail());

        if (user1.getEmail().equalsIgnoreCase( user.getEmail()) && user1.getPassword().equalsIgnoreCase( user.getPassword())) {
            Session s = new Session();
            s.setUser(user1);
            s.setToken("dbgdshgbfdjhkgbfghjfdgbvdjbvkf");
            sessionRepository.save(s);
            return "login in sucessfully";
        }

        return "login failed";

    }

}
