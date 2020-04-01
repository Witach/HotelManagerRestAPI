package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "user_role")
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
