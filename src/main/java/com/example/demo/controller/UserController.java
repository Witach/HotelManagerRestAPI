package com.example.demo.controller;

import com.example.demo.entity.AppUser;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getUser(@PathVariable long id){
        return ResponseEntity.ok(
                userRepository.findById(id).orElseThrow(
                        IllegalArgumentException::new
                )
        );
    }

    @PostMapping
    public ResponseEntity<AppUser> postUser(@RequestBody AppUser appUser){
        return ResponseEntity.ok(
                userRepository.save(appUser)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppUser> putUser(@RequestBody AppUser appUser, @PathVariable long id){
        if(!userRepository.existsById(id))
            throw new IllegalArgumentException("User doesn't exists");

        var newUser = userRepository.save(appUser);
        return ResponseEntity.ok(newUser);
    }
}
