package com.book.mybook.model.dto.services;

import org.springframework.stereotype.Service;

import com.book.mybook.model.dto.book.BookDtoBase;
import com.book.mybook.model.entities.Book;

@Service
public class BookConverter {

    public Book BookDtoBaseToBook(BookDtoBase bookDto)
    {
        Book newBook = Book
        .builder()
        .title(bookDto.getTitle())
        .author(bookDto.getAuthor())
        .isbn(bookDto.getIsbn())
        .available(true)
        .build();

        return newBook;
    }

}
