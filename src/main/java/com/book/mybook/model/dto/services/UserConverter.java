package com.book.mybook.model.dto.services;

import org.springframework.stereotype.Service;

import com.book.mybook.model.dto.users.UserDtoBase;
import com.book.mybook.model.entities.User;

@Service
public class UserConverter 
{
    public User userDtoBaseToUser(UserDtoBase userDto)
    {
        User newUser = User
        .builder()
        .role(userDto.getRole())
        .password(userDto.getPassword())
        .username(userDto.getUsername())
        .build();

        return newUser;
    }
}
