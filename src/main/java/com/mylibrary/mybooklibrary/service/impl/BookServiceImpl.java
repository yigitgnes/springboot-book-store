package com.mylibrary.mybooklibrary.service.impl;

import com.mylibrary.mybooklibrary.exception.BookNotFoundException;
import com.mylibrary.mybooklibrary.model.Book;
import com.mylibrary.mybooklibrary.repository.BookRepository;
import com.mylibrary.mybooklibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository repo;

    public List<Book> listAll(){
        return repo.findAll();
    }

    public void save(Book book) {
        repo.save(book);
    }

    public Book get(Integer id) throws BookNotFoundException {
        Optional<Book> result = repo.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw new BookNotFoundException("Could not find any books with ID "+id);
    }

    public void delete(Integer id) throws BookNotFoundException {
        Long count = repo.countById(id);
        if (count==null || count ==0){
            throw new BookNotFoundException("Could not find any books with ID "+id);
        }
        repo.deleteById(id);
    }
}
