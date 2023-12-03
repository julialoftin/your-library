package com.example.controllers;

import com.example.models.Book;
import com.example.models.data.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    BookRepository bookRepository;

    @RequestMapping("/")
    public String renderHomePage(Model model) {
        model.addAttribute("title", "Your Library");
        model.addAttribute("books", bookRepository.findAll());
        return "index";
    }





}
