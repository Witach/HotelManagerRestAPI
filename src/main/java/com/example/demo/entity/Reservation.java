package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    Long id;

    @ManyToMany
    @JoinTable(
            joinColumns = {@JoinColumn(name = "reservation_id", referencedColumnName = "reservation_id")},
            inverseJoinColumns = {@JoinColumn(name = "room_id", referencedColumnName = "room_id")}
    )
    Set<Room> roomSet;

    @OneToOne
    @JoinColumn(name = "bill_id")
    Bill bill;

    @Column(name = "from_date")
    LocalDateTime fromDate;

    @Column(name = "to_date")
    LocalDateTime toDate;

}
