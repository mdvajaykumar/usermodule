package com.example.registrationlogindemo.repositories;

import com.example.registrationlogindemo.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface SessionRepository extends JpaRepository<Session, UUID> {

    Session save(Session sesion);
}
