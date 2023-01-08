package com.project.server.services;

import com.project.server.daos.Book;

import com.project.server.dtos.BookDto;
import com.project.server.exceptions.RecordNotFoundException;
import com.project.server.mappers.BookMapper;
import com.project.server.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookDto> getAll() {
        return bookMapper.map(bookRepository.findAll());
    }

    public BookDto getBookById(String id) {
        return bookRepository.findById(id)
                .map(bookMapper::map)
                .orElseThrow(() -> new RecordNotFoundException(1, "Book Not Found"));
    }

    @Transactional
    public void addBook(BookDto book) {
        Book entity = bookMapper.map(book);
        bookRepository.save(entity);
    }
}
