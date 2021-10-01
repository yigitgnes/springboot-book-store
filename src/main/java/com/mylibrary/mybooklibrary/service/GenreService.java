package com.mylibrary.mybooklibrary.service;

import com.mylibrary.mybooklibrary.exception.GenreNotFoundException;
import com.mylibrary.mybooklibrary.model.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> listAll();

    void save(Genre name);

    Genre get(Integer id) throws GenreNotFoundException;

    void delete(Integer id) throws GenreNotFoundException;
}
