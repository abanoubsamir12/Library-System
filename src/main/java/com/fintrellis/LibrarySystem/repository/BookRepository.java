package com.fintrellis.LibrarySystem.repository;


import com.fintrellis.LibrarySystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByName(String name);
}
