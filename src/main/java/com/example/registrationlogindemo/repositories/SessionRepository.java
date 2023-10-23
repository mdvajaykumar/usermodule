package com.example.registrationlogindemo.repositories;

import com.example.registrationlogindemo.models.Session;
import com.example.registrationlogindemo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    Session save(Session sesion);

    Optional<Session> findByTokenAndUser(String token, User user);
}
