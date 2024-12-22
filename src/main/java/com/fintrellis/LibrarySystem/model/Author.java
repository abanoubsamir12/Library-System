package com.fintrellis.LibrarySystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "authors")
public class Author extends User {


    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> books;

    @Column(name ="about_me")
    private String about_me;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getAbout_me() {
        return about_me;
    }

    public void setAbout_me(String about_me) {
        this.about_me = about_me;
    }

    public Author() {}

    public Author(String email, String username, String password, List<Book> books,String about_me) {
        super(email, username, password);
        this.books = books;
        this.about_me=about_me;
    }

}
