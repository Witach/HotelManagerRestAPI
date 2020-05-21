package com.example.demo.controller;

import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.User;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id){
        return ResponseEntity.ok(
                userRepository.findById(id).orElseThrow(
                        IllegalArgumentException::new
                )
        );
    }

    @PostMapping
    public ResponseEntity<User> postUser(@RequestBody User user){
        return ResponseEntity.ok(
                userRepository.save(user)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> putUser(@RequestBody User user, @PathVariable long id){
        if(!userRepository.existsById(id))
            throw new IllegalArgumentException("User doesn't exists");

        var newUser = userRepository.save(user);
        return ResponseEntity.ok(newUser);
    }
}
