package com.fintrellis.LibrarySystem.controller;

import com.fintrellis.LibrarySystem.dto.AuthorDTO;
import com.fintrellis.LibrarySystem.dto.BookDTO;
import com.fintrellis.LibrarySystem.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    // Get all authors
    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    // Get author by ID
    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.getAuthorById(id));
    }

    // Create a new author
    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO authorDTO) {
        return ResponseEntity.ok(authorService.createAuthor(authorDTO));
    }

    // Add a new book to the author's book list
    @PostMapping("/{authorId}/addBook")
    public ResponseEntity<AuthorDTO> addBookToAuthor(@PathVariable Long authorId, @RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(authorService.addBookToAuthor(authorId, bookDTO));
    }

    // Delete an author
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.ok("Author deleted successfully.");
    }
}
