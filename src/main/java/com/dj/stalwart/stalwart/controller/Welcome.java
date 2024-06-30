package com.dj.stalwart.stalwart.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/greeting")
public class Welcome {

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the Stalwart Server!";
    }

}
