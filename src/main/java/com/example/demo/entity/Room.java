package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    UUID id;

    String roomNumber;

    Integer area;

    Integer amountOfPersons;

    Integer review;

    Boolean isEmpty;


    @ManyToMany(mappedBy = "roomSet")
    Set<Reservation> reservationSet;

    @ManyToMany
    @JoinColumn(name = "room_type")
    Set<RoomType> roomTypeSet;

    @ManyToMany
    @JoinColumn(name = "tag_name")
    Set<Tag> tagSet;
}
