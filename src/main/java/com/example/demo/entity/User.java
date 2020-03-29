package com.example.demo.entity;

import com.example.demo.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    UUID id;

    @Column(unique = true)
    String email;

    @JsonIgnore
    String password;

     Role role;

}
