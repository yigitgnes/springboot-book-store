package com.mylibrary.mybooklibrary.repository;

import com.mylibrary.mybooklibrary.model.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    public Long countById(Integer id);

}
