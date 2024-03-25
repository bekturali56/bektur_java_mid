package com.example.bektur_java_mid.service.impl;

import com.example.bektur_java_mid.dto.UserResponse;
import com.example.bektur_java_mid.entity.User;
import com.example.bektur_java_mid.exception.BadRequestException;
import com.example.bektur_java_mid.exception.NotFoundException;
import com.example.bektur_java_mid.repo.UserRepository;
import com.example.bektur_java_mid.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public void register(String email) {
        if (userRepository.findByEmail(email).isPresent())
            throw new BadRequestException("user with this email is already exist!");
        User user = new User();
        user.setEmail(email);
        userRepository.save(user);
    }

    @Override
    public UserResponse getById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty())
            throw new NotFoundException("user with this id is not exist!", HttpStatus.NOT_FOUND);

        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.get().getId());
        userResponse.setEmail(user.get().getEmail());
        return userResponse;
    }
}
