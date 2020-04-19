package com.example.demo.controller;

import com.example.demo.config.Messages;
import com.example.demo.entity.Reservation;
import com.example.demo.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ReservationController {

    private ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/reservation")
    public ResponseEntity<Reservation> makeReservation(@RequestBody Reservation reservation){
        log.info(Messages.makeMessageForController("POST [/reservation]"));
        Reservation addedEntity = reservationService.addReservation(reservation);
        return ResponseEntity.ok(addedEntity);
    }
}
