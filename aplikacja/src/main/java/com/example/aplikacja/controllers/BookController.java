package com.example.aplikacja.controllers;

import com.example.aplikacja.model.Book;
import com.example.aplikacja.repositories.BookRepository;
import com.example.aplikacja.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private final BookService service;
    public BookController(BookService service){
        this.service=service;
    }
    @GetMapping("/all")
    public List<Book> getAllBooks(){
        List<Book> books = service.getAllBooks();
        return books;
    }
    @GetMapping("/byId/{id}")
    public Book getBookById(@PathVariable Long id){
        Book book = service.getBookById(id);
        return book;
    }
    @PostMapping("/add")
    public Book createBook(@RequestBody Book book) {
        return service.createBook(book);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        Book book = service.getBookById(id);
        if(book==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Book newBook = service.updateBook(id,bookDetails);

        return new ResponseEntity<>(newBook, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable Long id) {
        service.deleteBook(id);

    }

}
