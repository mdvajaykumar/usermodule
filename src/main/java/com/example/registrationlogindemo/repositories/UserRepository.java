package com.example.registrationlogindemo.repositories;

import com.example.registrationlogindemo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

   User save(User user);

   Optional<User> findByEmail(String email);

   @Override
   Optional<User> findById(Long userId);
}
