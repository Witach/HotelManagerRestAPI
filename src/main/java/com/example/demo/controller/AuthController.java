package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.exceptions.UserAlreadyExistsException;
import com.example.demo.model.UserModel;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.CREATED;


@Controller
public class AuthController {

    UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserModel user) {
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
}
