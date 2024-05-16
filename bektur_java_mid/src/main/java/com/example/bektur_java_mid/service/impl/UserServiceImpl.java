package com.example.bektur_java_mid.service.impl;

import com.example.bektur_java_mid.config.EmailSenderService;
import com.example.bektur_java_mid.config.JwtService;
import com.example.bektur_java_mid.dto.UserResponse;
import com.example.bektur_java_mid.entity.User;
import com.example.bektur_java_mid.exception.BadRequestException;
import com.example.bektur_java_mid.exception.NotFoundException;
import com.example.bektur_java_mid.repo.UserRepository;
import com.example.bektur_java_mid.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final EmailSenderService senderService;
    @Override
    public void register(String email, String password) {
        if (userRepository.findByEmail(email).isPresent())
            throw new BadRequestException("user with this email is already exist!");
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        String code = UUID.randomUUID().toString();
        try {
            senderService.sendEmail(user.getEmail(), "Please, confirm ur email!", "http://localhost:8888/user/confirm?confirm=" + code);
            user.setCode(code);
        }catch (Exception e){
            throw new BadRequestException("could not find/send confirmation to your email!");
        }

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

    @Override
    public String login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty())
            throw new NotFoundException("user with this email is not exist!", HttpStatus.NOT_FOUND);
        if (!user.get().getCode().equals("confirmed"))
            throw new BadRequestException("confirm your email please!");

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        }
        catch (Exception e){
            throw new BadRequestException("bad credentials");
        }

        return jwtService.generateToken(user.get());
    }

    @Override
    public String confirm(String code) {
        Optional<User> user = userRepository.findByCode(code);
        if (user.isPresent()){
            user.get().setCode("confirmed");
            userRepository.save(user.get());
            return "successfully confirmed!";
        }
        return "please, try again, something is wrong";
    }
}