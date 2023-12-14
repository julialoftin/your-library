package com.example.controllers;

import com.example.models.data.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    BookRepository bookRepository;

    @RequestMapping("/index")
    public String renderHomePage(Model model) {
        model.addAttribute("title", "Your Library");
        model.addAttribute("books", bookRepository.findAll());
        return "index";
    }

}
