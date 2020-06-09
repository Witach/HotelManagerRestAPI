package com.example.demo.controller;

import com.example.demo.entity.Bill;
import com.example.demo.entity.Reservation;
import com.example.demo.repository.BillRepository;
import com.sun.mail.iap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/bills")
public class BillController {

    @Autowired
    BillRepository billRepository;

    @GetMapping
    public ResponseEntity<Page<Bill>> getBill(Pageable pageable){
        var page = billRepository.findAll(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bill> getBill(@PathVariable long id){
        var bill = billRepository.findById(id)
                .orElseThrow(
                        IllegalArgumentException::new
                );
        return ResponseEntity.ok(bill);
    }

    @GetMapping("/statistics")
    public ResponseEntity<List<List<Object>>> getBillStatisticsFromLastMonth(){
        var stats = billRepository.getStatistics(LocalDate.now().minusMonths(1));
        return ResponseEntity.ok(stats);
    }

    @PostMapping
    public ResponseEntity<Bill> postBill(@RequestBody Bill bill){
        var newBill = billRepository.save(bill);
         URI uri = linkTo(methodOn(BillController.class).getBill(newBill.getId())).toUri();
        return ResponseEntity
                .created(uri)
                .body(newBill);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBill(@RequestBody Bill bill, @PathVariable long id){
        if(!billRepository.existsById(id))
            throw new IllegalArgumentException("This bill doesn't exits");
        var newBill = billRepository.save(bill);
        return ResponseEntity.ok(newBill);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteBill(@PathVariable long id){
        billRepository.deleteById(id);
        return HttpStatus.NO_CONTENT;
    }

}
