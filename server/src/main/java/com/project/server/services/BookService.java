package com.project.server.services;

import com.project.server.daos.Book;

import com.project.server.dtos.BookDto;
import com.project.server.exceptions.RecordNotFoundException;
import com.project.server.mappers.BookMapper;
import com.project.server.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
;
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
                .orElseThrow(() -> new RecordNotFoundException("Book Not Found"));
    }

    @Transactional
    public BookDto addBook(BookDto book) {
        Book entity = bookMapper.map(book);
        return bookMapper.map(bookRepository.save(entity));
    }

    @Transactional
    public BookDto updateBook(String id, BookDto book) {
        Book entity = bookRepository.findById(id)
               .orElseThrow(() -> new RecordNotFoundException("Book Not Found"));

        bookMapper.mapTo(entity, book);
        bookRepository.save(entity);
        return bookMapper.map(entity);
    }

    @Transactional
    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }
}
