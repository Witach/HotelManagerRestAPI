package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "bill")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    Long id;

    Double price;

    @OneToOne(mappedBy = "bill")
    @JoinColumn(name = "reservation_id")
    Reservation reservation;

    @OneToMany(mappedBy = "bill")
    Set<Person> tenant;

    @ManyToOne
    @JoinColumn( name = "administrator_id")
    User administrator;

}
