package com.example.demo.entity;

import com.example.demo.validators.ReservationBetweenDays;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@Table(name = "reservation")
@ReservationBetweenDays
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    Long id;

    @ManyToMany
    @NotBlank
    @JoinTable(
            joinColumns = {@JoinColumn(name = "reservation_id", referencedColumnName = "reservation_id")},
            inverseJoinColumns = {@JoinColumn(name = "room_id", referencedColumnName = "room_id")}
    )
    Set<Room> roomSet;

    @OneToOne
    @JoinColumn(name = "bill_id")
    Bill bill;

    @Column(name = "from_date")
    @NotBlank
    @FutureOrPresent
    LocalDate fromDate;

    @Column(name = "to_date")
    @NotBlank
    @Future
    LocalDate toDate;

}
