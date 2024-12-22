package com.fintrellis.LibrarySystem.service;

import com.fintrellis.LibrarySystem.dto.AuthorDTO;
import com.fintrellis.LibrarySystem.dto.BookDTO;

import java.util.List;

public interface AuthorService {
    List<AuthorDTO> getAllAuthors();
    AuthorDTO getAuthorById(Long id);
    AuthorDTO createAuthor(AuthorDTO authorDTO);
    AuthorDTO addBookToAuthor(Long authorId, BookDTO bookDTO);
    void deleteAuthor(Long id);
}
