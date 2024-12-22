package com.fintrellis.LibrarySystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customers")
public class Customer extends User {

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;


    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Customer(

    ) {}

    public Customer(String email, String username, String password, Book book) {
        super(email, username, password);
        this.book = book;
    }

    public Customer(String email, String username,String password) {
        super(email, username, password);
    }
}
