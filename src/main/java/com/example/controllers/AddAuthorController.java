package com.example.controllers;

import com.example.models.Author;
import com.example.models.data.AuthorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("author")
public class AddAuthorController {

    @Autowired
    AuthorRepository authorRepository;

    @GetMapping("index")
    public String displayAllAuthors(Model model) {
        model.addAttribute("title", "All Authors");
        model.addAttribute("authors", authorRepository.findAll());

        return "author/index";
    }

    @GetMapping("add")
    public String displayAddAuthorForm(Model model) {
        model.addAttribute("title", "Add an Author");
        model.addAttribute("author", new Author());

        return "author/add";
    }

    @PostMapping("add")
    public String processAddAuthorForm(@ModelAttribute @Valid Author newAuthor, Errors errors) {
        if (errors.hasErrors()) {
            return "author/add";
        }
        authorRepository.save(newAuthor);
        return "redirect:/author/index";
    }

}
