package com.example.demo.controller;

import com.example.demo.exceptions.UserAlreadyExistsException;
import com.example.demo.model.UserModel;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@Controller
public class AuthController {

    UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid UserModel user) {
        log.info("Received request POST [/register]");
        try {
            userService.addNewUser(user);
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
    public ResponseEntity<User> authenticate(){
     return ResponseEntity.ok(null);
    }

}
