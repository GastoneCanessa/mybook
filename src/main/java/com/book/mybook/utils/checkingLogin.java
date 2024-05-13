package com.book.mybook.utils;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.book.mybook.model.repositories.UserRepository;

public class checkingLogin {

    
    // @Autowired
    // static private BCryptPasswordEncoder passwordEncoder;

    // @Autowired
    // static private UserRepository uRepo;

    // static public boolean checkLogin(String username, String password) {
    //     // Recupera l'hash della password dal database
    //     String storedPasswordHash = uRepo.findByUsername(username).get().getPassword();
        
    //     // Verifica la password
    //     return passwordEncoder.matches(password, storedPasswordHash);
    // }
}
