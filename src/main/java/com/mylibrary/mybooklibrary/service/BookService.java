package com.mylibrary.mybooklibrary.service;

import com.mylibrary.mybooklibrary.exception.BookNotFoundException;
import com.mylibrary.mybooklibrary.model.Book;

import java.util.List;

public interface BookService {

    List<Book> listAll();

    void save(Book book);

    Book get(Integer id) throws BookNotFoundException;

    void delete(Integer id) throws BookNotFoundException;
}
