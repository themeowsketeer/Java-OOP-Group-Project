package com.project.server.controllers;

import com.project.server.dtos.BookDto;
import com.project.server.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDto> getAllBooks() {
        return bookService.getAll();
    }

    @PostMapping
    public ResponseEntity<BookDto> addBook(
            @RequestBody BookDto book
    ) {
        BookDto persistedBook = bookService.addBook(book);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(persistedBook);
    }

}
