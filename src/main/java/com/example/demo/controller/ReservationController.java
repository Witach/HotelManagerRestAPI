package com.example.demo.controller;

import com.example.demo.config.Messages;
import com.example.demo.entity.Reservation;
import com.example.demo.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
public class ReservationController {

    private ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Transactional
    @PostMapping("/reservation")
    public ResponseEntity<Reservation> makeReservation(@RequestBody @Valid Reservation reservation){
        log.info(Messages.makeMessageForController("POST [/reservation]"));
        Reservation addedEntity = reservationService.addReservation(reservation);
        return ResponseEntity.ok(addedEntity);
    }
}
