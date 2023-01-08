package com.project.server.controllers;

import com.project.server.daos.Book;
import com.project.server.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAll();
    }

    @PostMapping
    public ResponseEntity<Book> addBook(
            @RequestBody Book book
    ) {
        bookService.addBook(book);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(book);
    }

}
