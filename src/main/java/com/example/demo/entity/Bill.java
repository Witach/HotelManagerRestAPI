package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    UUID id;

    Double price;

    @OneToOne
    Reservation reservation;

    @ManyToOne
    Person tenant;

    @ManyToOne
    User administrator;

}
