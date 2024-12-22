package com.fintrellis.LibrarySystem.dto;

import com.fintrellis.LibrarySystem.model.Book;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

public class AuthorDTO extends UserDTO {

    private String about_me;
    private List<String> booksList;
    public AuthorDTO() {}

    public String getAbout_me() {
        return about_me;
    }

    public void setAbout_me(String about_me) {
        this.about_me = about_me;
    }

    public List<String> getBooksList() {
        return booksList;
    }

    public void setBooksList(List<String> booksList) {
        this.booksList = booksList;
    }

    public AuthorDTO(String password, String email, String username, String about_me, List<String> booksList) {
        super(password, email, username);
        this.about_me = about_me;
        this.booksList=booksList;
    }

    public AuthorDTO(Long id, String email, String username, Object o) {
        super(id,email,username);
    }

    public AuthorDTO(Long id, String email, String username, String aboutMe, List<String> collect) {
        super(id, email, username);
        this.about_me = aboutMe;
        this.booksList=collect;
    }
}
