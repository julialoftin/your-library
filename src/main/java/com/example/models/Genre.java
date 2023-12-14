package com.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Genre extends AbstractEntity {

    @NotNull
    private String name;

    @OneToMany(mappedBy = "genre")
    private Set<Book> books = new HashSet<>();

    public Genre() {
    }

    public Genre(String name) {
        super();
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
