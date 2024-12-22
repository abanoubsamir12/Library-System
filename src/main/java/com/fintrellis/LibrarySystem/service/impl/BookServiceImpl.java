package com.fintrellis.LibrarySystem.service.impl;

import com.fintrellis.LibrarySystem.Exceptions.DuplicateNameException;
import com.fintrellis.LibrarySystem.Exceptions.NotFoundException;
import com.fintrellis.LibrarySystem.dto.BookDTO;
import com.fintrellis.LibrarySystem.model.Author;
import com.fintrellis.LibrarySystem.model.Book;
import com.fintrellis.LibrarySystem.repository.AuthorRepository;
import com.fintrellis.LibrarySystem.repository.BookRepository;
import com.fintrellis.LibrarySystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;


    public void checkDuplicateName(String name)
    {
        if(bookRepository.existsByName(name))
            throw new DuplicateNameException("Name: '" + name + "' already exists");
    }


    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream().map(book -> new BookDTO(book.getId(),book.getName(),book.getIsAvailable()
                ,book.getDescription(), book.getCustomer() != null? book.getCustomer().getId(): null,
                book.getAuthor() != null? book.getAuthor().getId(): null)).collect(Collectors.toList());
    }

    @Override
    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new NotFoundException("Book with ID " + id + " not found"));
        BookDTO res=  new BookDTO(book.getId(),book.getName(),book.getIsAvailable(),book.getDescription(),
                book.getCustomer() != null? book.getCustomer().getId(): null,
                book.getAuthor() != null? book.getAuthor().getId(): null);
        return res;
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        checkDuplicateName(bookDTO.getName());
        Book book = new Book(bookDTO.getName(),bookDTO.getIsAvailable(),bookDTO.getDescription());
        bookDTO.setId(book.getId());
        Author author = authorRepository.findById(bookDTO.getAuthorId()).orElseThrow(()-> new NotFoundException("Author with ID " + bookDTO.getAuthorId() + " not found"));
        book.setAuthor(author);
        bookRepository.save(book);
        return bookDTO;
    }

    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Book book = bookRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Book with ID " + id + " not found"));
        if(!book.getName().equals(bookDTO.getName()))
            checkDuplicateName(bookDTO.getName());

        book.setName(bookDTO.getName());
        book.setIsAvailable(bookDTO.getIsAvailable());
        if(!bookDTO.getAuthorId().equals(book.getAuthor().getId()))
        {
            Author author = authorRepository.findById(bookDTO.getAuthorId()).orElseThrow(()-> new NotFoundException("Author with ID " + id + " not found"));
            book.setAuthor(author);
        }
        bookRepository.save(book);
        return bookDTO;
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new NotFoundException("Book with ID " + id + " not found"));
        bookRepository.delete(book);
    }
}
