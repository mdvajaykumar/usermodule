package com.example.registrationlogindemo.service;

import com.example.registrationlogindemo.dto.UserDto;
import com.example.registrationlogindemo.exception.UserNotFoundException;
import com.example.registrationlogindemo.models.Role;
import com.example.registrationlogindemo.models.User;
import com.example.registrationlogindemo.repositories.RoleRepository;
import com.example.registrationlogindemo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;


    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;

        this.roleRepository = roleRepository;
    }

//    @Override
//    public User register(User user) {
//        return userRepository.save(user);
//    }
//
//    @Override
//    public String login(User user) {
////        User user1 = userRepository.findByEmail(user.getEmail());
////
////        if (user1.getEmail().equalsIgnoreCase( user.getEmail()) && user1.getPassword().equalsIgnoreCase( user.getPassword())) {
////            Session s = new Session();
////            s.setUser(user1);
////            s.setToken("dbgdshgbfdjhkgbfghjfdgbvdjbvkf");
////            sessionRepository.save(s);
////            return "login in sucessfully";
////        }
//
//        return "login failed";
//
//    }

    @Override
    public User getUserDetails(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("user Not Found with given Id");
        } else {
            return userOptional.get();
        }
    }

    @Override
    public UserDto setUserDetails(Long userId, List<Long> roleIds) {
        Optional<User> userOptional = userRepository.findById(userId);
        List<Role> roles = roleRepository.findAllByIdIn(roleIds);


        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("user not Found with Id");
        }

        User user = userOptional.get();
        user.setRoles(Set.copyOf(roles));

        User savedUser = userRepository.save(user);
        return UserDto.from(savedUser);

    }
}
