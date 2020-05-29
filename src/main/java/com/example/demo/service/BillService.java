package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.BillRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service
public class BillService {


    BillRepository billRepository;
    ReservationRepository reservationRepository;
    UserRepository userRepository;


    @Autowired
    public BillService(BillRepository billRepository, ReservationRepository reservationRepository, UserRepository userRepository) {
        this.billRepository = billRepository;
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
    }

    public Bill createBillFromReservation(Reservation reservation, String name) throws NoSuchElementException{
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservation.getId());
        Reservation reservationFromDB = reservationOptional.orElseThrow();
        Room room = reservation.getRoom();
        SecurityContext securityContext = SecurityContextHolder.getContext();
        AppUser appUser = userRepository.findUserByEmail(name).orElseThrow();
        Person person = appUser.getPerson();

        LocalDate fromDate = reservationFromDB.getFromDate();
        LocalDate toDate = reservationFromDB.getToDate();
        long days = ChronoUnit.DAYS.between(fromDate,toDate);

        double price = room.getPrice() * days;

        Bill bill = Bill.builder()
                .price(price)
                .reservation(reservation)
                .tenant(person)
                .build();
        billRepository.save(bill);
        return bill;
    }


}
