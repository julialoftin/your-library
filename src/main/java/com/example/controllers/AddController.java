package com.example.controllers;

import com.example.models.Book;
import com.example.models.data.AuthorRepository;
import com.example.models.data.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("add")
    public String displayAddForm(Model model) {
        model.addAttribute("title", "Add a Book");
        model.addAttribute("book", new Book());
        model.addAttribute("books", bookRepository.findAll());
        return "add";
    }

    @PostMapping("add")
    public String processAddForm(@ModelAttribute @Valid Book newBook, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "add";
        }

        bookRepository.save(newBook);
        return "redirect:/";
    }

}
