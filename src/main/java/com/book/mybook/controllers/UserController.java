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
    public ResponseEntity<?> postUser(@RequestBody UserDtoBase userDto) {
        // Verifica se esiste già un utente con lo stesso username
        Optional<User> existingUser = uRepo.findByUsername(userDto.getUsername());
        if (existingUser.isPresent()) {
            // Se l'utente esiste già, restituisci un messaggio di errore
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already in use.");
        }
        // Converte il DTO in un oggetto User
        User newUser = uConv.userDtoBaseToUser(userDto);
        // Salva il nuovo utente nel database
        User savedUser = uRepo.save(newUser);
        // Restituisci il nuovo utente salvato
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return uRepo.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUser(@PathVariable Integer id) {
        // Cerca l'utente nel database per ID
        Optional<User> user = uRepo.findById(id);

        // Controlla se l'utente è presente
        if (user.isPresent()) {
            // Se l'utente è trovato, restituisci l'utente con stato HTTP 200 (OK)
            return ResponseEntity.ok(user.get());
        } else {
            // Se l'utente non è trovato, restituisci uno stato HTTP 404 (Not Found)
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUSer(@PathVariable Integer id) {
        Optional<User> op = uRepo.findById(id);
        if (op.isPresent()) {
            uRepo.deleteById(id);
            return new ResponseEntity<String>("User deleted", HttpStatus.OK);
        } else
            return new ResponseEntity<String>("No user with id " + id, HttpStatus.NOT_FOUND);
    }

}
