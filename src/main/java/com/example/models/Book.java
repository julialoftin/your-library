package com.example.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Book extends AbstractEntity {

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id", nullable = true, foreignKey = @ForeignKey(name = "FK_Book_Author",
            foreignKeyDefinition = "FOREIGN KEY (`author_id`) REFERENCES `author` (`id`) ON DELETE SET NULL"))
    private Author author;

    @NotNull
    private String title;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    private Integer isbn;

    public Book() {
    }

    public Book(Author author, String title, Integer isbn) {
        super();
        this.author = author;
        this.title = title;
        this.isbn = isbn;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Integer getIsbn() {
        return isbn;
    }

    public void setIsbn(Integer isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author=" + author +
                ", title='" + title + '\'' +
                ", isbn=" + isbn +
                '}';
    }
}
