package com.example.bektur_java_mid.service;

import com.example.bektur_java_mid.dto.UserResponse;

public interface UserService {
    void register(String email);

    UserResponse getById(Long userId);
}
