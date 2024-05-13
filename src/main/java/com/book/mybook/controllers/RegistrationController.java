package com.book.mybook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.mybook.model.dto.services.UserConverter;
import com.book.mybook.model.dto.users.UserDtoBase;
import com.book.mybook.model.entities.User;
import com.book.mybook.model.repositories.UserRepository;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserConverter uConv;

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody UserDtoBase userDto) {
        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username is already taken.");
        }
        User newUser = uConv.userDtoBaseToUser(userDto);
        userRepository.save(newUser);
        return ResponseEntity.ok("User registered successfully");
    }
}