package com.dj.stalwart.stalwart.controller;

import com.dj.stalwart.stalwart.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/greeting")
public class Welcome {

    @Autowired
    LoginService loginService;

    @GetMapping("/welcome")
    public String welcome() {

        return "Welcome to the Stalwart Server!";
    }

}
