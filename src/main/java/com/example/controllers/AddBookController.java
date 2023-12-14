package com.example.controllers;

import com.example.models.Author;
import com.example.models.Book;
import com.example.models.Genre;
import com.example.models.data.AuthorRepository;
import com.example.models.data.BookRepository;
import com.example.models.data.GenreRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddBookController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    GenreRepository genreRepository;

    @GetMapping("add")
    public String displayAddForm(Model model) {
        model.addAttribute("title", "Add a Book");
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("allGenres", genreRepository.findAll());
        return "add";
    }

    @PostMapping("add")
    public String processAddForm(@ModelAttribute @Valid Book newBook, @RequestParam("author.id") Integer authorId,
                                 @RequestParam("genre.id") Integer genreId,
                                 Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add a Book");
            return "add";
        }
        // Check if the author ID is set in the form submission
        if (authorId == null) {
            // Handle the case where the author ID is not set
            model.addAttribute("errorMessage", "Author is required");
            model.addAttribute("title", "Add a Book");
            return "add";
        }

        // Retrieve the Author object from the repository using the ID
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid author ID: " + authorId));

        // Set the retrieved author in the Book object
        newBook.setAuthor(author);

        // Check if the genre ID is set in the form submission
        if (genreId == null) {
            model.addAttribute("errorMessage", "Genre is required");
            model.addAttribute("title", "Add a Book");
            return "add";
        }

        // Retrieve the Genre object from the repository using the ID
        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid genre ID: " + genreId));

        // Set the retrieved genre in the Book object
        newBook.setGenre(genre);

        // Continue with saving the book
        bookRepository.save(newBook);
        return "redirect:/";
    }

}
