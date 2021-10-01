package com.mylibrary.mybooklibrary.controller;


import com.mylibrary.mybooklibrary.model.Book;
import com.mylibrary.mybooklibrary.model.Genre;
import com.mylibrary.mybooklibrary.service.BookService;
import com.mylibrary.mybooklibrary.exception.BookNotFoundException;
import com.mylibrary.mybooklibrary.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private GenreService genreService;

    @GetMapping("/books")
    public String showBookList(Model model) {
        List<Book> listBooks = bookService.listAll();
        model.addAttribute("listBooks", listBooks);

        return "books";
    }

    @GetMapping("/books/new")
    public String showNewForm(Model model) {
        List<Genre> genreList = this.genreService.listAll();
        model.addAttribute("book", new Book());
        model.addAttribute("pageTitle", "Add New Book");
        model.addAttribute("genreList", genreList);
        return "book_form";
    }

    @PostMapping("/books/save")
    public String saveBook(Book book, RedirectAttributes ra) {
        bookService.save(book);
        ra.addFlashAttribute("message", "The Book Has Been Saved Successfully");
        return "redirect:/books";
    }

    @GetMapping("/books/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        List<Genre> genreList = this.genreService.listAll();
        try {
            Book book = bookService.get(id);
            model.addAttribute("book", book);
            model.addAttribute("pageTitle", "Edit Book (ID: " + id + ")");
            model.addAttribute("genreList", genreList);
            return "book_form";
        } catch (BookNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/books";
        }
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            bookService.delete(id);
            ra.addFlashAttribute("message","The Book ID "+id+" Has Been Deleted.");

        } catch (BookNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/books";
    }
}
