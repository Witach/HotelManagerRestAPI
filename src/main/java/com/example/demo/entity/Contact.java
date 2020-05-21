package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    Long id;

    @Column(name = "phone_number")
    @NotNull
    @Size(min = 9, max = 9)
    String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @NotNull
    @JsonBackReference
    Person person;

}
