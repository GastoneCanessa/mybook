package com.book.mybook.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.book.mybook.model.dto.services.UserConverter;
import com.book.mybook.model.dto.users.UserDtoBase;
import com.book.mybook.model.entities.User;
import com.book.mybook.model.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class UserController {

    @Autowired
    UserRepository uRepo;

    @Autowired
    UserConverter uConv;

    @PostMapping("/users")
    public ResponseEntity<?> postUser(@RequestBody UserDtoBase userDto) 
    {
        User newUser = uConv.userDtoBaseToUser(userDto);
        return ResponseEntity.ok(uRepo.save(newUser));
    }

    @GetMapping("/users")
    public List<User> getUsers(@RequestParam String param) {
        return uRepo.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Integer id) {
        return uRepo.findById(id).get();
    }
    
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUSer(@PathVariable Integer id)
    {
        Optional<User> op = uRepo.findById(id);
        if(op.isPresent())
        {
            uRepo.deleteById(id);
            return new ResponseEntity<String>("User deleted", HttpStatus.OK);
        }
        else
            return new ResponseEntity<String>("No user with id " + id, HttpStatus.NOT_FOUND);
    }

}
