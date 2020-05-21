package com.example.demo.controller;

import com.example.demo.config.Messages;
import com.example.demo.entity.Reservation;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.service.ReservationService;
import com.sun.mail.iap.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/reservation")
public class ReservationController {


    private ReservationService reservationService;
    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationController(ReservationService reservationService, ReservationRepository reservationRepository) {
        this.reservationService = reservationService;
        this.reservationRepository = reservationRepository;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Reservation> makeReservation(@RequestBody @Valid Reservation reservation){
        log.info(Messages.makeMessageForController("POST [/reservation]"));
        Reservation addedEntity = reservationService.addReservation(reservation);
        return ResponseEntity.ok(addedEntity);
    }

    @GetMapping
    public ResponseEntity<Page<Reservation>> getReservations(Pageable pageable){
        return ResponseEntity.ok(reservationRepository.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservation(@PathVariable long id){
        var reservation = reservationRepository.findById(id)
                .orElseThrow(
                        IllegalArgumentException::new
                );

        return ResponseEntity.ok(reservation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable long id, Reservation reservation){
        if(!reservationRepository.existsById(id))
            throw new IllegalArgumentException("No reservation with this id");
        var newReservation = reservationRepository.save(reservation);
        return ResponseEntity.ok(newReservation);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteReservation(@PathVariable long id){
        reservationRepository.deleteById(id);
        return HttpStatus.NO_CONTENT;
    }

}
