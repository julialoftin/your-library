package com.example.controllers;

import com.example.models.data.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DeleteController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("delete")
    public String displayDeleteForm(Model model) {
        model.addAttribute("title", "Delete a Book");
        model.addAttribute("books", bookRepository.findAll());

        return "delete";
    }

    @PostMapping("delete")
    public String processDeleteForm(@RequestParam(name = "selectedBooks", required = false) List<Integer> selectedBookIds,
                                    Model model) {
        if (selectedBookIds != null && !selectedBookIds.isEmpty()) {
            for (Integer bookId : selectedBookIds) {
                bookRepository.deleteById(bookId);
            }
        }
        return "redirect:/";
    }

}
