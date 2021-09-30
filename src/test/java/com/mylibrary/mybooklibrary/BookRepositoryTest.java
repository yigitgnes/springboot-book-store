package com.mylibrary.mybooklibrary;

import com.mylibrary.mybooklibrary.model.Book;
import com.mylibrary.mybooklibrary.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class BookRepositoryTest {

    @Autowired
    private BookRepository repo;

    @Test
    public void testAddNew() {
        Book book = new Book();
        book.setName("The Lord of The Rings: The Return of The Kingsss");
        book.setAuthor("J.R.R. Tolkien");
        book.setGenre("Science Fiction");
        book.setAvailable(true);
        book.setScore(10.0F);

        Book savedBook = repo.save(book);
        assertThat(savedBook).isNotNull();
        assertThat(savedBook.getId()).isGreaterThan(0);

    }

    @Test
    public void testListAll() {
        Iterable<Book> books = repo.findAll();
        assertThat(books).hasSizeGreaterThan(0);

        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void testUpdate() {
        Integer bookId = 1;
        Optional<Book> optionalBook = repo.findById(bookId);
        Book book = optionalBook.get();
        book.setName("The Lord of The Rings: The Fellowship of The Ring");

        Book updatedBook = repo.findById(bookId).get();
        assertThat(updatedBook.getName()).isEqualTo("The Lord of The Rings: The Fellowship of The Ring");

    }

    @Test
    public void testGet(){
        Integer bookId = 1;
        Optional<Book> optionalBook = repo.findById(bookId);
        assertThat(optionalBook).isPresent();
        System.out.println(optionalBook.get());

    }

    @Test
    public void testDelete(){
        Integer bookId=5;
        repo.deleteById(bookId);

        Optional<Book> optionalBook = repo.findById(bookId);
        assertThat(optionalBook).isNotPresent();
    }

}
