package com.mylibrary.mybooklibrary.repository;

import com.mylibrary.mybooklibrary.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
