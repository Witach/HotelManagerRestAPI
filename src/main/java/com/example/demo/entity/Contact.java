package com.example.demo.entity;


import lombok.Data;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
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
    @NotBlank
    @Size(min = 9, max = 9)
    String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @NotBlank
    Person person;

}
