package com.example.bektur_java_mid.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String hello(){
        return "Hello, Bekturali!";
    }
}
