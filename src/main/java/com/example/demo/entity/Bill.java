package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@ToString
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    Long id;

    @Min(0)
    Double price;

    @OneToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "reservation_id")
    @NotNull
    Reservation reservation;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tenant_id")
    @NotNull
    Person tenant;

    public Bill() {
    }
    public Bill(int id) {
    }

    public Long getId() {
        return id;
    }

    public Double getPrice() {
        return price;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public Person getTenant() {
        return tenant;
    }

    public static Builder builder(){
        return new Builder();
    }


    public static class Builder {

        private Bill bill = new Bill();

        public Builder price(Double price) {
            bill.setPrice(price);
            return this;
        }

        public Builder reservation(Reservation reservation) {
            bill.setReservation(reservation);
            return this;
        }

        public Builder tenant(Person tenant) {
            bill.setTenant(tenant);
            return this;
        }

        public Bill build() {
            return bill;
        }

    }

}
