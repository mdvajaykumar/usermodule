package com.example.registrationlogindemo.models;


import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User extends  BaseModel {
    private  String email;
    private  String password;
}
