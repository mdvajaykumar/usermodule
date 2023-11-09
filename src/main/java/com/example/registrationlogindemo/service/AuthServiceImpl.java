package com.example.registrationlogindemo.service;


import com.example.registrationlogindemo.dto.UserDto;
import com.example.registrationlogindemo.exception.PasswordIncorrectException;
import com.example.registrationlogindemo.exception.SessionNotFoundException;
import com.example.registrationlogindemo.exception.UserNotFoundException;
import com.example.registrationlogindemo.models.Session;
import com.example.registrationlogindemo.models.SessionStatus;
import com.example.registrationlogindemo.models.User;
import com.example.registrationlogindemo.repositories.SessionRepository;
import com.example.registrationlogindemo.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMapAdapter;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private  UserRepository userRepository;

    private  SessionRepository sessionRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;



    public AuthServiceImpl(UserRepository userRepository, SessionRepository sessionRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.bCryptPasswordEncoder =bCryptPasswordEncoder;

    }

    @Override
    public ResponseEntity<UserDto> login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
           throw new UserNotFoundException("User Not Found");
        }

        User user = userOptional.get();

        if (!user.getPassword().equalsIgnoreCase(password)) {
             throw new PasswordIncorrectException("username or password is incorrect");
        }

//        String token = RandomStringUtils.randomAlphanumeric(30);

        MacAlgorithm alg = Jwts.SIG.HS256; //or HS384 or HS256
        SecretKey key = alg.key().build();


        Map<String, Object> jsonForJwt = new HashMap<>();
        jsonForJwt.put("email", user.getEmail());
        jsonForJwt.put("roles", user.getRoles());
        jsonForJwt.put("createdAt", new Date());
        jsonForJwt.put("expiryAt", new Date(LocalDate.now().plusDays(3).toEpochDay()));


       String token =Jwts.builder().claims(jsonForJwt).
                signWith(key,alg).compact();


        Session s = new Session();
        s.setUser(user);
        s.setToken(token);
        s.setSessionStatus(SessionStatus.ACTIVE);
        s.setExpiryAt(new Date());
        sessionRepository.save(s);


//        UserDto userDto = new UserDto();

        MultiValueMapAdapter<String, String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE, "auth-token:" + token);


        ResponseEntity<UserDto> response = new ResponseEntity<>(UserDto.from(userOptional.get()),headers, HttpStatus.OK);

        return response;


    }

    @Override
    public UserDto signup(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);

        return UserDto.from(user);
    }

    @Override
    public ResponseEntity<Void> logout(String token, long userId) {
        Optional<User> optionalUser = userRepository.findById(userId) ;
        if (optionalUser.isEmpty()) {
         throw new UserNotFoundException("user not Found");
        }

        Optional<Session> optionalSession = sessionRepository.findByTokenAndUser(token, optionalUser.get());

        if (optionalSession.isEmpty()) {
             throw new SessionNotFoundException("session not Found");
        }

        Session session = optionalSession.get();
        session.setSessionStatus(SessionStatus.ENDED);
        sessionRepository.save(session);

        return ResponseEntity.ok().build();

    }

    @Override
    public SessionStatus validateToken(String token, long userId) {

        Optional<User> optionalUser = userRepository.findById(userId) ;
        Optional<Session> session = sessionRepository.findByTokenAndUser(token, optionalUser.get());

        if (session.isEmpty()) {
                throw new SessionNotFoundException("session is not there");
        }

        return session.get().getSessionStatus();


    }
}
