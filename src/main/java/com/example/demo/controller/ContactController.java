package com.example.demo.controller;

import com.example.demo.entity.Bill;
import com.example.demo.entity.Contact;
import com.example.demo.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/contacts")
public class ContactController {
    @Autowired
    ContactRepository contactRepository;

    @GetMapping
    public ResponseEntity<Page<Contact>> getContact(Pageable pageable){
        var page = contactRepository.findAll(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContact(@PathVariable long id){
        var bill = contactRepository.findById(id)
                .orElseThrow(
                        IllegalArgumentException::new
                );
        return ResponseEntity.ok(bill);
    }

    @PostMapping
    public ResponseEntity<Contact> postContact(@RequestBody Contact contact){
        var newContact = contactRepository.save(contact);
        URI uri = linkTo(methodOn(BillController.class).getBill(newContact.getId())).toUri();
        return ResponseEntity
                .created(uri)
                .body(newContact);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateContact(@RequestBody Contact contact, @PathVariable long id){
        if(!contactRepository.existsById(id))
            throw new IllegalArgumentException("This bill doesn't exits");
        var newContact = contactRepository.save(contact);
        return ResponseEntity.ok(newContact);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteContact(@PathVariable long id){
        contactRepository.deleteById(id);
        return HttpStatus.NO_CONTENT;
    }
}
