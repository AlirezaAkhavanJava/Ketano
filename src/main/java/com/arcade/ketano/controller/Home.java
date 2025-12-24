package com.arcade.ketano.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {

    @GetMapping("")
    public String message() {
        return "SPRING BOOT APPLICATION IS RUNNING";
    }
}
