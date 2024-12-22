package com.fintrellis.LibrarySystem.dto;

import lombok.Getter;
import lombok.Setter;

public class BookDTO {

    private Long id;
    private String name;
    private Boolean isAvailable;
    private String description;
    private Long customerId; // ID of the customer who borrowed the book
    private Long authorId;   // ID of the book's author

    public BookDTO(Long id, String name, String description, Boolean isAvailable) {
        this.id=id;
        this.name=name;
        this.description=description;
        this.isAvailable=isAvailable;
    }

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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public BookDTO() {}

    public BookDTO(Long id, String name, Boolean isAvailable,String description ,Long customerId, Long authorId) {
        this.id = id;
        this.name = name;
        this.isAvailable = isAvailable;
        this.description=description;
        this.customerId = customerId;
        this.authorId = authorId;
    }
}
