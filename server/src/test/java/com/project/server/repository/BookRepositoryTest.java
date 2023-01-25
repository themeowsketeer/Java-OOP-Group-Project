package com.project.server.repository;

import com.project.server.daos.Author;
import com.project.server.daos.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class BookRepositoryTest {
    @Autowired BookRepository bookRepository;

    @Test
    void findAllShouldProduceAllBooks() {
        List<Book> books = bookRepository.findAll();
        assertThat(books).hasSize(2);
    }

    @Test
    void saveBooksShouldAutoGenerateIdForEachAuthor() {
        Set<Author> authors = Set.of(
                new Author(null, "Christina Hobbs"),
                new Author(null, "Lauren Billings")
        );
        Book book = Book.builder()
                .id("3")
                .name("The Unhoneymooners")
                .authors(authors)
                .releasedYear(2019L)
                .edition(1)
                .placedAt(new Date())
                .quantity(10L)
                .build();
        Book persistedBook = bookRepository.save(book);
        assertThat(persistedBook).isNotNull();
        assertThat(persistedBook.getAuthors()).isNotNull()
                .allSatisfy(author -> assertThat(author.getId()).isNotNull());
    }

}
