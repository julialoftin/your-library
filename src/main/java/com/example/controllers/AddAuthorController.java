package com.example.controllers;

import com.example.models.Author;
import com.example.models.data.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddAuthorController {

    @Autowired
    AuthorRepository authorRepository;

    @GetMapping("author/add")
    public String displayAddAuthorForm(Model model) {
        model.addAttribute("author", new Author());

        return "author/add";
    }

}
