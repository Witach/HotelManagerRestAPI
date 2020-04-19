package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
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
    @BatchSize(size = 10)
    @JsonBackReference
    @JoinTable(
            joinColumns = {@JoinColumn(name = "room_id", referencedColumnName = "room_id")},
            inverseJoinColumns = {@JoinColumn(name = "room_type_id", referencedColumnName = "room_type_id")}
    )
    Set<RoomType> roomTypeSet;

    @ManyToMany
    @BatchSize(size = 10)
    @JsonBackReference
    @JoinTable(
            joinColumns = {@JoinColumn(name = "room_id", referencedColumnName = "room_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "tag_id")}
    )
    Set<Tag> tagSet;
}
