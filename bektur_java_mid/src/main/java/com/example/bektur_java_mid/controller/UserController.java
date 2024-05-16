package com.example.bektur_java_mid.controller;

import com.example.bektur_java_mid.dto.UserResponse;
import com.example.bektur_java_mid.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public void register(@RequestParam String email, @RequestParam String password){
        userService.register(email, password);
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password){
        return userService.login(email, password);
    }

    @GetMapping("/confirm")
    public String confir(@RequestParam String code){
        return userService.confirm(code);
    }


    @GetMapping("/{userId}")
    public UserResponse userResponse(@PathVariable Long userId){
        return userService.getById(userId);
    }

}
