package com.fintrellis.LibrarySystem.repository;

import com.fintrellis.LibrarySystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
