package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "person")
@Getter
@Setter
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    Long id;

    @Column(name = "first_name")
    @NotNull
    @Size(min = 3, max = 32)
    String firstName;

    @Column(name = "last_name")
    @NotNull
    @Size(min = 3, max = 32)
    String lastName;

    @JsonBackReference
    @OneToMany(mappedBy = "tenant")
    Set<Bill> bill;

    @JsonBackReference
    @OneToOne(mappedBy = "person")
    @NotNull
    User user;

    @JsonManagedReference
    @OneToMany(mappedBy = "person")
    Set<Contact> contactSet;

    public  Person(Long id){
        this.id = id;
    }

    public Person(){
        this.contactSet = new HashSet<>();
        this.bill = new HashSet<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }

}
