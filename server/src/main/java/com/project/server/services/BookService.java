package com.project.server.services;

import com.project.server.models.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BookService {
    private List<Book> bookRepository = Arrays.asList(
            new Book("Norwegian Woods", "Haruki", 2002, 1),
            new Book("Love", "sth", 2002, 12),
            new Book("Yay", "af)", 2001, 12)
    );

    public List<Book> getAll() {
        return bookRepository;
    }

    public void addBook(Book book) {
        bookRepository.add(book);
    }
}
