package com.mylibrary.mybooklibrary.controller;

import com.mylibrary.mybooklibrary.exception.GenreNotFoundException;
import com.mylibrary.mybooklibrary.model.Genre;
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
public class GenreController {
    @Autowired
    private GenreService genreService;

    @GetMapping("/genres")
    public String showGenreList(Model model) {
        List<Genre> listGenres = genreService.listAll();
        model.addAttribute("listGenres", listGenres);

        return "genres";
    }

    @GetMapping("/genre/new")
    public String showNewForm(Model model) {
        model.addAttribute("genre", new Genre());
        model.addAttribute("pageTitle", "Add New Genre");

        return "genre_form";
    }

    @PostMapping("/genre/save")
    public String saveGenre(Genre genre, RedirectAttributes ra) {
        this.genreService.save(genre);
        ra.addFlashAttribute("message", "The Genre Has Been Saved Successfully");

        return "redirect:/genres";
    }

    @GetMapping("/genre/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Genre genre = this.genreService.get(id);
            model.addAttribute("genre", genre);
            model.addAttribute("pageTitle", "Genre (Name: " + genre.getName() + ")");
            return "genre_form";
        } catch (GenreNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());

            return "redirect:/genres";
        }
    }

    @GetMapping("/genre/delete/{id}")
    public String deleteGenre(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            this.genreService.delete(id);
            ra.addFlashAttribute("message","The Genre ID "+id+" Has Been Deleted.");

        } catch (GenreNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/genres";
    }
}
