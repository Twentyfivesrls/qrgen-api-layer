package com.twentyfive.qrgenapilayer.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
@CrossOrigin(origins = "*")
public class PublicController {
    @RequestMapping("/hello")
    public String hello(){
        return "Hello World";
    }
}
