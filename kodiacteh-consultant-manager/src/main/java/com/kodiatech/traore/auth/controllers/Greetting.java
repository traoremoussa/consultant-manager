package com.kodiatech.traore.auth.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/gre")
public class Greetting {

@GetMapping("/")
    public ResponseEntity<String> greting(){
        return ResponseEntity.ok("hello from our api");
    }
}
