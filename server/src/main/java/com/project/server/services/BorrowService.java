package com.project.server.services;

import com.project.server.daos.Book;
import com.project.server.daos.BorrowBook;
import com.project.server.daos.UserEntity;
import com.project.server.dtos.BorrowDto;
import com.project.server.exceptions.OutOfBookException;
import com.project.server.exceptions.RecordNotFoundException;
import com.project.server.mappers.BorrowBookMapper;
import com.project.server.repository.BookRepository;
import com.project.server.repository.BorrowBookRepository;
import com.project.server.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BorrowService {
    private final BorrowBookRepository borrowBookRepository;
    private final BorrowBookMapper borrowMapper;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Autowired
    public BorrowService(
            BorrowBookRepository borrowBookRepository,
            BorrowBookMapper borrowMapper,
            BookRepository bookRepository,
            UserRepository userRepository) {
        this.borrowBookRepository = borrowBookRepository;
        this.borrowMapper = borrowMapper;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public List<BorrowDto> getAllIssuedBooks() {
        return borrowMapper.map(borrowBookRepository.findBorrowBooksByReturnedAtIsNull());
    }

    public List<BorrowDto> getIssuedBookForUser(long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("User not found"));
        return borrowMapper.map(borrowBookRepository.findBorrowBooksByUser(user));
    }

    @Transactional
    public BorrowDto issueBookByUserIdAndBookId(String bookId, long userId) {
        Book bookEntity = bookRepository.findById(bookId)
                .orElseThrow(() -> new RecordNotFoundException("Book not found"));
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new RecordNotFoundException("User not found"));
        var quantity = bookEntity.getQuantity();
        if (quantity == 0) {
            throw new OutOfBookException("Out of books");
        }
        bookEntity.setQuantity(quantity - 1);
        bookRepository.save(bookEntity);
        BorrowBook borrowBook = BorrowBook.builder()
                .book(bookEntity)
                .user(userEntity)
                .issuedAt(new Date())
                .build();
        return borrowMapper.map(borrowBookRepository.save(borrowBook));
    }

    public List<BorrowDto> getAllReturnedBooks() {
        return borrowMapper.map(borrowBookRepository.findBorrowBooksByReturnedAtNotNull());
    }

    @Transactional
    public BorrowDto returnedBookByBorrowBookId(long id) {
        BorrowBook borrowEntity = borrowBookRepository
                .findBorrowBookByIdAndReturnedAtIsNull(id)
                .orElseThrow(() -> new RecordNotFoundException("Borrow book not found"));
        Book bookEntity = borrowEntity.getBook();
        bookEntity.setQuantity(bookEntity.getQuantity() + 1);
        bookRepository.save(bookEntity);
        borrowEntity.setReturnedAt(new Date());
        return borrowMapper.map(borrowBookRepository.save(borrowEntity));
    }
}
