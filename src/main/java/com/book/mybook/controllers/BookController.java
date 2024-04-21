package com.book.mybook.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.book.mybook.model.dto.book.BookDtoBase;
import com.book.mybook.model.dto.book.BookPutRqst;
import com.book.mybook.model.dto.services.BookConverter;
import com.book.mybook.model.entities.Book;
import com.book.mybook.model.repositories.BookkRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class BookController {

    @Autowired
    BookkRepository bRepo;

    @Autowired
    BookConverter bConv;
    
    @PostMapping("/book")
    public ResponseEntity<?>  postBook(@RequestBody BookDtoBase bookDTO) 
    {
        Book newBook = bConv.BookDtoBaseToBook(bookDTO);
        return ResponseEntity.ok(bRepo.save(newBook));
    }
    
    @GetMapping("/books")
    public List<Book> getBooks() 
    {
        return bRepo.findAll();
    }
    
    @GetMapping("/book/{id}")
    public Book getBook(@PathVariable Integer id) 
    {
        return bRepo.findById(id).get();
    };

    @PutMapping("/book/{id}}")
    public ResponseEntity<?> putBook(@PathVariable Integer id, @RequestBody BookPutRqst bookPutRqst) 
    {
        Optional<Book> book = bRepo.findById(id);
        if (book.isPresent()) {
            Book existingBook = book.get();
            existingBook.setAuthor(bookPutRqst.getAuthor());
            existingBook.setAvailable(bookPutRqst.getAvailable());
            existingBook.setIsbn(bookPutRqst.getIsbn());
            existingBook.setTitle(bookPutRqst.getTitle());
            
            Book savedBook = bRepo.save(existingBook);

            return new ResponseEntity<Book>(savedBook, HttpStatus.OK);
        }else
        return new ResponseEntity<String>("No book with id " + id, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) 
    {
        Optional<Book> book = bRepo.findById(id);
        if (book.isPresent()) {
            bRepo.deleteById(id);
            return new ResponseEntity<String>("Book deleted", HttpStatus.OK);
        } else
            return new ResponseEntity<String>("No book with id " + id, HttpStatus.NOT_FOUND);

    }


}



