package com.example.bektur_java_mid.service;

import com.example.bektur_java_mid.dto.UserResponse;

public interface UserService {
    void register(String email, String password);

    UserResponse getById(Long userId);

    String login(String email, String password);

    String confirm(String code);
}
