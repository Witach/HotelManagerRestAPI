package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@ToString
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    Long id;

    @Min(0)
    Double price;

    @OneToOne(mappedBy = "bill")
    @JoinColumn(name = "reservation_id")
    @NotBlank
    Reservation reservation;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    @NotBlank
    Person tenant;

    @ManyToOne
    @JoinColumn(name = "administrator_id")
    @NotBlank
    User administrator;

    public Bill() {
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

        public Builder administrator(User administrator) {
            bill.setAdministrator(administrator);
            return this;
        }

        public Bill build() {
            return bill;
        }

    }

}
