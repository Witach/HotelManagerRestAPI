package com.example.demo.controller;

import com.example.demo.entity.AppUser;
import com.example.demo.entity.Contact;
import com.example.demo.entity.Person;
import com.example.demo.exceptions.UserAlreadyExistsException;
import com.example.demo.model.UserModel;
import com.example.demo.repository.ContactRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@Controller
public class AuthController {

    UserService userService;
    ContactRepository contactRepository;
    PersonRepository personRepository;

    @Autowired
    public AuthController(UserService userService, ContactRepository contactRepository, PersonRepository personRepository) {
        this.userService = userService;
        this.contactRepository = contactRepository;
        this.personRepository =personRepository;
    }

    @Transactional
    @PostMapping("/register")
    @CrossOrigin
    public ResponseEntity<String> register(@RequestBody @Valid UserModel user) {
        log.info("Received request POST [/register]");
        try {
            AppUser createdUser = userService.addNewUser(user);
        } catch (UserAlreadyExistsException exception) {
            return ResponseEntity
                    .status(CONFLICT)
                    .body(exception.getMessage());
        }

        return ResponseEntity
                .status(CREATED)
                .build();
    }

    @CrossOrigin
    @PostMapping("/auth")
    public ResponseEntity<User> authenticate() {
        return ResponseEntity.ok(null);
    }

}
