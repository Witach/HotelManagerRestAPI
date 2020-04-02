package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@RestController
public class LogInOutController {

    @GetMapping("/secured")
    public ResponseEntity<String> secured(HttpServletRequest request){
        Arrays.stream(request.getCookies()).forEach(cookie -> System.out.println(cookie.getName()));
        return ResponseEntity.ok("DUPA");
    }


}
