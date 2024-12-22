package com.fintrellis.LibrarySystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO extends UserDTO {

    private Long bookId; // ID of the book assigned to the customer

    public CustomerDTO() {}

    public CustomerDTO(Long id, String email, String username, Long bookId) {
        super(id, email, username);
        this.bookId = bookId;
    }

    public CustomerDTO(Long id, String email, String username) {
        super(id, email, username);
    }
    public CustomerDTO(String password, String email, String username) {
        super(password,email,username);
    }

}
