package com.example.demo.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    Long id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    Reservation reservation;

    @OneToMany(mappedBy = "tenant")
    Set<Bill> bill;

    @OneToOne
    User user;

    @OneToMany(mappedBy = "person")
    Set<Contact> contactSet;

}
