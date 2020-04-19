package com.example.demo.controller;

import com.example.demo.entity.Contact;
import com.example.demo.model.ContactModel;
import com.example.demo.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.example.demo.config.Messages.*;

@Slf4j
@RestController
public class PersonDataController {

    ContactService contactService;

    @Autowired
    public PersonDataController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/contact")
    public ResponseEntity<?> createContact(@RequestBody ContactModel contact){
        log.info(makeMessageForController("POST [/contact]"));
        Contact newContact = contactService.addContact(contact);
        Link link = new Link("/contact/"+ newContact.getId());
        return ResponseEntity.created(link.toUri()).build();
    }
}
