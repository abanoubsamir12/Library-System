package com.fintrellis.LibrarySystem.dto;

import lombok.Getter;
import lombok.Setter;

public class UserDTO {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private Long id;
    private String email;
    private String username;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    public UserDTO() {}

    public UserDTO(String password, String email, String username) {
        this.password = password;
        this.email = email;
        this.username = username;
    }
    public UserDTO(Long id, String email, String username) {
        this.id = id;
        this.email = email;
        this.username = username;
    }

}
