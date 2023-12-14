package com.example.controllers;

import com.example.models.Genre;
import com.example.models.data.GenreRepository;
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
@RequestMapping("genre")
public class AddGenreController {

    @Autowired
    GenreRepository genreRepository;

    @GetMapping("index")
    public String displayAllGenres(Model model) {
        model.addAttribute("title", "Genres");
        model.addAttribute("allGenres", genreRepository.findAll());

        return "genre/index";
    }

    @GetMapping("add")
    public String displayAddGenreForm(Model model) {
        model.addAttribute("title", "Add a Genre");
        model.addAttribute("genre", new Genre());

        return "genre/add";
    }

    @PostMapping("add")
    public String processAddGenreForm(@ModelAttribute @Valid Genre newGenre, Errors errors) {
        if (errors.hasErrors()) {
            return "add";
        }

        genreRepository.save(newGenre);
        return "redirect:/genre/add";

    }

}
