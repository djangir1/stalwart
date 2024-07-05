package com.dj.stalwart.stalwart.controller;

import com.dj.stalwart.stalwart.entity.Login;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/patient")
public class PatientController {



    @GetMapping("/add")
    public String addPatient() {
        Login login = new Login(1,9928510775l,1,1,"temp", "Active",LocalDateTime.now(),LocalDateTime.now());
        return login.toString();
    }
}
