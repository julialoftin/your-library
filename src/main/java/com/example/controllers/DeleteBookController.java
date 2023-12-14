package com.example.controllers;

import com.example.models.Author;
import com.example.models.Book;
import com.example.models.data.AuthorRepository;
import com.example.models.data.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DeleteBookController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @GetMapping("delete")
    public String displayDeleteForm(Model model) {
        model.addAttribute("title", "Delete a Book");
        model.addAttribute("books", bookRepository.findAll());

        return "delete";
    }

    private void deleteBook(Integer bookId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book != null) {
            Author author = book.getAuthor();

            if (author != null) {
                if (author.getBooks().isEmpty()) {
                    authorRepository.deleteById(author.getId());
                }
            }
            bookRepository.deleteById(bookId);
        }
    }

    @PostMapping("delete")
    public String processDeleteForm(@RequestParam(name = "selectedBooks", required = false) List<Integer> selectedBookIds,
                                    Model model) {
        if (selectedBookIds != null && !selectedBookIds.isEmpty()) {
            for (Integer bookId : selectedBookIds) {
                deleteBook(bookId);
            }
        }
        return "redirect:/";
    }


}
