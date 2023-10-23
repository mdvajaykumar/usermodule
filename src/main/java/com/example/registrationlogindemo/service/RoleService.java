package com.example.registrationlogindemo.service;


import com.example.registrationlogindemo.models.Role;
import com.example.registrationlogindemo.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {

   Role saveRole(Role role);

}
