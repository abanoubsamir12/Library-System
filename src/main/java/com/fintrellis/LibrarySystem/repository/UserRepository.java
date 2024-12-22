package com.fintrellis.LibrarySystem.repository;



import com.fintrellis.LibrarySystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query methods can be defined here

    User findByEmail(String email); // Example: Find user by email

    User findByUsername(String username); // Example: Find user by username
}

