package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.dto.RoleRequestDto;
import com.example.registrationlogindemo.models.Role;
import com.example.registrationlogindemo.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private  RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody RoleRequestDto request) {
        Role role = new Role();
        role.setRole(request.getName());
        Role role1 = roleService.saveRole(role);
        return new ResponseEntity<>(role1, HttpStatus.OK);
    }
}
