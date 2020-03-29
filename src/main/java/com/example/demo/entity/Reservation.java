package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    UUID id;

    @ManyToMany
    @JoinColumn(name = "reservation_id")
    Set<Room> roomSet;
}
