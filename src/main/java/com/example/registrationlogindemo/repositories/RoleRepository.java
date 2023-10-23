package com.example.registrationlogindemo.repositories;

import com.example.registrationlogindemo.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository  extends JpaRepository<Role,Long> {

   List<Role> findAllByIdIn(List<Long> roleId);
    Role save(Role role);
}
