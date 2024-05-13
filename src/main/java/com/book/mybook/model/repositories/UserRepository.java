package com.book.mybook.model.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.mybook.model.entities.User;

public interface UserRepository extends JpaRepository <User, Integer>
{
    Optional<User> findByUsername(String username);
}
