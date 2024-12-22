package com.fintrellis.LibrarySystem.service.impl;

import com.fintrellis.LibrarySystem.Exceptions.DuplicateNameException;
import com.fintrellis.LibrarySystem.Exceptions.NotFoundException;
import com.fintrellis.LibrarySystem.dto.AuthorDTO;
import com.fintrellis.LibrarySystem.dto.BookDTO;
import com.fintrellis.LibrarySystem.model.Author;
import com.fintrellis.LibrarySystem.model.Book;
import com.fintrellis.LibrarySystem.repository.AuthorRepository;
import com.fintrellis.LibrarySystem.repository.BookRepository;
import com.fintrellis.LibrarySystem.service.AuthorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(author -> new AuthorDTO(
                        author.getPassword(),
                        author.getEmail(),
                        author.getUsername(),
                        author.getAbout_me(),
                        author.getBooks().stream().map(Book::getName).collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }

    @Override
    public AuthorDTO getAuthorById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Book with ID " + id + " not found"));
        return new AuthorDTO(
                author.getId(),
                author.getEmail(),
                author.getUsername(),
                author.getAbout_me(),
                author.getBooks().stream().map(Book::getName).collect(Collectors.toList())
        );
    }

    public void checkUsernameDuplicate(String username)
    {
        if(authorRepository.existsByUsername(username))
            throw  new DuplicateNameException("username : " + username + "already exists");
    }
    public void checkEmailDuplicate(String email)
    {
        if(authorRepository.existsByUsername(email))
            throw  new DuplicateNameException("email : " + email + "already exists");
    }

    @Override
    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        checkEmailDuplicate(authorDTO.getEmail());
        checkUsernameDuplicate(authorDTO.getUsername());

        Author author = new Author();
        author.setEmail(authorDTO.getEmail());
        author.setUsername(authorDTO.getUsername());
        author.setAbout_me(authorDTO.getAbout_me());
        author.setPassword("hashedPassword"); // Use PasswordEncoder for real implementation

        authorRepository.save(author);
        return new AuthorDTO(author.getId(), author.getEmail(), author.getUsername(),null);
    }

    @Transactional
    @Override
    public AuthorDTO addBookToAuthor(Long authorId, BookDTO bookDTO) {
        // Find the author
        Author author = authorRepository.findById(authorId)
                .orElseThrow(()-> new NotFoundException("Author with ID " + authorId + " not found"));

        if(bookRepository.existsByName(bookDTO.getName()))
            throw new DuplicateNameException("Name: " + bookDTO.getName() + "already exists" );
        // Create and save the new book
        Book book = new Book();
        book.setName(bookDTO.getName());
        book.setIsAvailable(true);
        book.setDescription(bookDTO.getDescription());
        book.setAuthor(author); // Set the book's author

        bookRepository.save(book);

        // Update author's book list
        if(author.getBooks().equals(null))
            author.setBooks(new ArrayList<Book>());

        List<Book> booksList = author.getBooks();
        if(author.getBooks()==null)
        {
            booksList = new ArrayList<Book>();

        }
        booksList.add(book);
        author.setBooks(booksList);
        authorRepository.save(author);

        return new AuthorDTO(
                author.getId(),
                author.getEmail(),
                author.getUsername(),
                author.getBooks().stream()
                        .map(b -> new BookDTO(b.getId(), b.getName(), b.getDescription(), b.getIsAvailable()))
                        .collect(Collectors.toList())
        );
    }

    @Override
    public void deleteAuthor(Long id) {
        try {
            authorRepository.deleteById(id);
        }
        catch (Error error){
            throw new NotFoundException("Not found author with id : " + id);
        }
    }
}
