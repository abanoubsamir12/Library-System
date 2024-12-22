package com.fintrellis.LibrarySystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "books")
public class Book {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean available) {
        isAvailable = available;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable=true;

    @Column(name="description")
    private String description;

    @ManyToOne
    @JoinColumn(name="author_id")
    private Author author;


    @OneToOne(mappedBy = "book", cascade = CascadeType.ALL)
    private Customer customer;

    // Default constructor
    public Book() {}

    // Parameterized constructor
    public Book(String name, Boolean isAvailable,String description) {
        this.name = name;
        this.isAvailable = isAvailable;
        this.description = description;
    }
}
