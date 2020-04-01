package com.example.demo.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id")
    Long id;

    @Column(unique = true)
    String name;

    String description;

    @ManyToMany(mappedBy = "role")
    Set<User> user;

}
