package com.example.registrationlogindemo.service;


import com.example.registrationlogindemo.dto.UserDto;
import com.example.registrationlogindemo.models.Session;
import com.example.registrationlogindemo.models.SessionStatus;
import com.example.registrationlogindemo.models.User;
import com.example.registrationlogindemo.repositories.SessionRepository;
import com.example.registrationlogindemo.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMapAdapter;

import java.util.HashMap;
import java.util.Optional;

public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final SessionRepository sessionRepository;

    public AuthServiceImpl(UserRepository userRepository, SessionRepository sessionRepository) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public ResponseEntity<UserDto> login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {

        }

        User user = userOptional.get();

        if (!user.getPassword().equals(password)) {

        }

        String token = RandomStringUtils.randomAlphanumeric(30);

        Session s = new Session();
        s.setUser(user);
        s.setToken(token);
        s.setSessionStatus(SessionStatus.ACTIVE);
        sessionRepository.save(s);


        UserDto userDto = new UserDto();

        MultiValueMapAdapter<String, String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE, "auth-token:" + token);


        ResponseEntity<UserDto> response = new ResponseEntity<>(userDto, headers, HttpStatus.OK);

        return response;


    }

    @Override
    public UserDto signup(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);

        return UserDto.from(user);
    }

    @Override
    public ResponseEntity<Void> logout(String token, long userId) {

        Optional<Session> optionalSession = sessionRepository.findByTokenAndUser(token, userId);

        if (optionalSession.isEmpty()) {

        }

        Session session = optionalSession.get();
        session.setSessionStatus(SessionStatus.ENDED);
        sessionRepository.save(session);


    return  ResponseEntity.ok().build();

    }

    @Override
    public SessionStatus validateToken(String token, long userId) {
       Optional<Session> session = sessionRepository.findByTokenAndUser(token, userId);

       if(session.isEmpty()){

       }

       return session.get().getSessionStatus();


    }
}
