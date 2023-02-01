package com.project.server.controllers;

import com.project.server.dtos.BorrowDto;
import com.project.server.services.BorrowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The controller for issue and return book REST API endpoint
 */
@RestController
@RequestMapping("api/issue")
public class IssueController {
    private final BorrowService borrowService;

    public IssueController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    /**
     * Handle GET request for the /api/issue endpoint
     * @return A JSON representation of the list of DTOs of a
     * borrowed book which includes the book, and the borrower
     */
    @GetMapping
    public List<BorrowDto> getAllIssuedBooks() {
         return borrowService.getAllIssuedBooks();
     }

    /**
     * Handle GET request for the /api/issue/{id} endpoint
     * Example: /api/issue/1
     * @param id
     *  * @return A JSON representation of the list of DTOs of a
     *  borrowed book which includes the book, and the borrower
     */
     @GetMapping("/{id}")
    public List<BorrowDto> getAllIssuedBooksForUser(
            @PathVariable long id
     ) {
        return borrowService.getIssuedBookForUser(id);
     }

    /**
     * Handle POST request for the /api/issue endpoint
     * Example URL: /api/issue?userid=5&bookid=12
     * Return one of the following status code:
     * 200 Ok: Successfully issue a book
     * 400 Bad Request: Out of Book
     * 401 Unauthorized: missing query parameters
     * @param userId
     * @param bookId
     * @return A JSON representation of the DTO of the borrowed book
     * that is added to the database
     */
     @PostMapping
    public BorrowDto addIssuedBook(
            @RequestParam("userid") long userId,
            @RequestParam("bookid") String bookId
     ) {
        return borrowService.issueBookByUserIdAndBookId(bookId, userId);
     }

    /**
     * Handle GET request for the /api/issue/return endpoint
     * @return A JSON representation of the list of the DTOs of the returned books
     */
    @GetMapping("/return")
    public List<BorrowDto> getReturnedBooks() {
         return borrowService.getAllReturnedBooks();
    }

    /**
     * Handle POST request for the /api/issue/return endpoint
     * Example: /api/issue/return?borrowid=1
     * 200 Ok: Successfully issue a book
     * 401 Unauthorized: missing query parameters
     * 404 Not Found: Borrow book not found
     * @param borrowId
     * @return A JSON representation of the DTO of the returned book
     */
    @PostMapping("/return")
    public BorrowDto returnBook(
            @RequestParam("borrowid") long borrowId
    ) {
        return borrowService.returnedBookByBorrowBookId(borrowId);
    }
}
