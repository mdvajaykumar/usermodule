package com.example.registrationlogindemo.service;

import com.example.registrationlogindemo.models.Role;
import com.example.registrationlogindemo.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository =roleRepository;
    }

    @Override
    public Role saveRole(Role role) {
       return  roleRepository.save(role);
    }
}
