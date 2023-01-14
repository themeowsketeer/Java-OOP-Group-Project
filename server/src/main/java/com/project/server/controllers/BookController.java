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

    @GetMapping(value = "/{id}")
    public BookDto addBook(@PathVariable String id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BookDto> addBook(
            @RequestBody BookDto book
    ) {
        BookDto persistedBook = bookService.addBook(book);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(persistedBook);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookDto updateBook(
            @PathVariable("id") String id,
            @RequestBody BookDto book
    ) {
        return bookService.updateBook(id, book);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(
            @PathVariable("id") String id
    ) {
        bookService.deleteBook(id);
    }

}
