package com.example.demo.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    UUID id;

    String phoneNumber;

    @ManyToOne
    Person person;

}
