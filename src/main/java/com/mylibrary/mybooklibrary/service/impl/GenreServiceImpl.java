package com.mylibrary.mybooklibrary.service.impl;

import com.mylibrary.mybooklibrary.exception.GenreNotFoundException;
import com.mylibrary.mybooklibrary.model.Genre;
import com.mylibrary.mybooklibrary.repository.GenreRepository;
import com.mylibrary.mybooklibrary.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<Genre> listAll() {
        return this.genreRepository.findAll();
    }

    @Override
    public void save(Genre genre) {
        this.genreRepository.save(genre);
    }

    @Override
    public Genre get(Integer id) throws GenreNotFoundException {
        return this.genreRepository.getById(id);
    }

    @Override
    public void delete(Integer id) throws GenreNotFoundException {
        this.genreRepository.deleteById(id);
    }
}
