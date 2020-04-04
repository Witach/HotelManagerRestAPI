package com.example.demo.entity;

import lombok.Builder;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    Long id;

    Double price;

    @OneToOne(mappedBy = "bill")
    @JoinColumn(name = "reservation_id")
    Reservation reservation;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    Person tenant;

    @ManyToOne
    @JoinColumn( name = "administrator_id")
    User administrator;

    public Bill() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Person getTenant() {
        return tenant;
    }

    public void setTenant(Person tenant) {
        this.tenant = tenant;
    }

    public User getAdministrator() {
        return administrator;
    }

    public void setAdministrator(User administrator) {
        this.administrator = administrator;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", price=" + price +
                ", reservation=" + reservation +
                ", tenant=" + tenant +
                ", administrator=" + administrator +
                '}';
    }
}
