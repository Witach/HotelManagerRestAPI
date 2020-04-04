package com.example.demo.service;

import com.example.demo.entity.Bill;
import com.example.demo.entity.Reservation;
import com.example.demo.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {


    BillService billService;
    SecurityContext securityContext = SecurityContextHolder.getContext();
    ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(BillService billService, ReservationRepository reservationRepository) {
        this.billService = billService;
    }

    public void addReservation(Reservation reservation){
         String userName = securityContext.getAuthentication().getName();
         Bill bill = billService.createBillFromReservation(reservation, userName);
         reservation.setBill(bill);
         reservationRepository.save(reservation);
    }
}
