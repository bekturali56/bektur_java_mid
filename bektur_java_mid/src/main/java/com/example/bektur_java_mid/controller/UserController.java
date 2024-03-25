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
    @PostMapping("/add")
    public void register(@RequestParam String email){
        userService.register(email);
    }
    @GetMapping("/{userId}")
    public UserResponse userResponse(@PathVariable Long userId){
        return userService.getById(userId);
    }

}
