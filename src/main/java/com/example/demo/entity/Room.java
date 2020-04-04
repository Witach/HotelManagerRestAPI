package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    Long id;

    @Column(name = "room_number")
    String roomNumber;

    @Column(name = "area")
    Integer area;

    @Column(name = "person_amount")
    Integer personAmount;

    String description;

    @Column(name = "room_price")
    Double price;

    @ManyToMany(mappedBy = "roomSet")
    Set<Reservation> reservationSet;

    @ManyToMany
    @JoinTable(
            joinColumns = {@JoinColumn(name = "room_id", referencedColumnName = "room_id")},
            inverseJoinColumns = {@JoinColumn(name = "room_type_id", referencedColumnName = "room_type_id")}
    )
    Set<RoomType> roomTypeSet;

    @ManyToMany
    @JoinTable(
            joinColumns = {@JoinColumn(name = "room_id", referencedColumnName = "room_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "tag_id")}
    )
    Set<Tag> tagSet;
}
