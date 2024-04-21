package com.book.mybook.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.mybook.model.entities.Book;

public interface BookkRepository extends JpaRepository <Book, Integer>
{

}
