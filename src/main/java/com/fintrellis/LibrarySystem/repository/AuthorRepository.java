package com.fintrellis.LibrarySystem.repository;

import com.fintrellis.LibrarySystem.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    boolean existsByUsername(String name);
    boolean existsByEmail(String name);
}