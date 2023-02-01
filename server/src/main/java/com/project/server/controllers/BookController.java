package com.project.server.controllers;

import com.project.server.dtos.BookDto;
import com.project.server.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The controller for accessing books REST API endpoints
 */
@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Handle GET request for /api/books
     * @return List of DTOs of books
     */
    @GetMapping
    public List<BookDto> getAllBooks() {
        return bookService.getAll();
    }

    /**
     * Handle GET request for /api/books/{id}
     * @param id the id of a book
     * @return The DTO of requested book
     */
    @GetMapping(value = "/{id}")
    public BookDto addBook(@PathVariable String id) {
        return bookService.getBookById(id);
    }

    /**
     * Handle POST request for /api/books
     * @param book the JSON representation for the book Object
     * @return the DTO of the book that is added to the database with the HTTP status Created
     */
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

    /**
     * Handle PUT request for /api/books/{id}
     * @param id
     * @param book
     * @return A JSON representation of the DTO of the updated book with the HTTP status OK
     */
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookDto updateBook(
            @PathVariable("id") String id,
            @RequestBody BookDto book
    ) {
        return bookService.updateBook(id, book);
    }

    /**
     * Handle DELETE request for /api/books/{id}, with a returned HTTP status of NO CONTENT
     * @param id
     */
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(
            @PathVariable("id") String id
    ) {
        bookService.deleteBook(id);
    }

}
