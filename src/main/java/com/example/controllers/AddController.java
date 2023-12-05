package com.example.controllers;

import com.example.models.Author;
import com.example.models.Book;
import com.example.models.data.AuthorRepository;
import com.example.models.data.BookRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @GetMapping("add")
    public String displayAddForm(Model model) {
        model.addAttribute("title", "Add a Book");
        model.addAttribute("book", new Book());
//        model.addAttribute("author", new Author());
        model.addAttribute("authors", authorRepository.findAll());
        return "add";
    }

    @PostMapping("add")
    public String processAddForm(@ModelAttribute @Valid Book newBook, @RequestParam("author.id") Integer authorId,
                                 Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "add";
        }
        // Check if the author ID is set in the form submission
        if (authorId == null) {
            // Handle the case where the author ID is not set
            model.addAttribute("errorMessage", "Author is required");
            return "add";
        }

        // Retrieve the Author object from the repository using the ID
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid author ID: " + authorId));

        // Set the retrieved author in the Book object
        newBook.setAuthor(author);

        // Continue with saving the book
        bookRepository.save(newBook);
        return "redirect:/";
    }

}
