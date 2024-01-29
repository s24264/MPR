package com.example.aplikacja.controllers;

import com.example.aplikacja.model.Author;
import com.example.aplikacja.repositories.AuthorRepository;
import com.example.aplikacja.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    @Autowired
    private final AuthorService service;
    public AuthorController(AuthorService service){
        this.service=service;
    }
    @GetMapping("/all")
    public List<Author> getAllAuthors() {
        List<Author> authors = service.getAllAuthors();
        return authors;
    }

    @GetMapping("/byId/{id}")
    public Author getAuthorById(@PathVariable Long id) {
        Author author = service.getAuthorById(id);
        return author;
    }

    @PostMapping("/add")
    public Author createAuthor(@RequestBody Author author) {
        return service.createAuthor(author);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author authorDetails) {
        Author author = service.getAuthorById(id);
        if(author==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Author newAuthor = service.updateAuthor(id,authorDetails);
        return new ResponseEntity<>(newAuthor,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        service.deleteAuthor(id);

    }
}
