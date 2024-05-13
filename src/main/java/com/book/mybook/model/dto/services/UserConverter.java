package com.book.mybook.model.dto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.book.mybook.model.dto.users.UserDtoBase;
import com.book.mybook.model.entities.User;

@Service
public class UserConverter 
{

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User userDtoBaseToUser(UserDtoBase userDto)
    {
        User newUser = User
        .builder()
        .role(userDto.getRole())
        .password(passwordEncoder.encode(userDto.getPassword()))
        .username(userDto.getUsername())
        .build();

        return newUser;
    }
}
