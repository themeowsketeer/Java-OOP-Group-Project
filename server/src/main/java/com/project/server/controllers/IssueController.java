package com.project.server.controllers;

import com.project.server.dtos.BorrowDto;
import com.project.server.services.BorrowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The controller for issue book REST API endpoint
 */
@RestController
@RequestMapping("api/issue")
public class IssueController {
    private final BorrowService borrowService;

    public IssueController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    /**
     * Handle the /api/issue endpoint
     */
    @GetMapping
    public List<BorrowDto> getAllIssuedBooks() {
         return borrowService.getAllIssuedBooks();
     }

    /**
     * Handle URL: /api/issue/id
     * Example: /api/issue/1
     * @param id
     */
     @GetMapping("/{id}")
    public List<BorrowDto> getAllIssuedBooksForUser(
            @PathVariable long id
     ) {
        return borrowService.getIssuedBookForUser(id);
     }

    /**
     * Handle URL: /api/issue?userid=?&bookid=?
     * Example URL: /api/issue?userid=5&bookid=12
     * Return one of the following status code:
     * 200 Ok: Successfully issue a book
     * 400 Bad Request: Out of Book
     * 401 Unauthorized: missing query parameters
     * @param userId
     * @param bookId
     */
     @PostMapping
    public BorrowDto addIssuedBook(
            @RequestParam("userid") long userId,
            @RequestParam("bookid") String bookId
     ) {
        return borrowService.issueBookByUserIdAndBookId(bookId, userId);
     }

    /**
     * Handle URL: /api/issue/return
     */
    @GetMapping("/return")
    public List<BorrowDto> getReturnedBooks() {
         return borrowService.getAllReturnedBooks();
    }

    /**
     * Handle URL: /api/issue/return?borrowid=?
     * Example: /api/issue/return?borrowid=1
     * 200 Ok: Successfully issue a book
     * 401 Unauthorized: missing query parameters
     * 404 Not Found: Borrow book not found
     * @param borrowId
     */
    @PostMapping("/return")
    public BorrowDto returnBook(
            @RequestParam("borrowid") long borrowId
    ) {
        return borrowService.returnedBookByBorrowBookId(borrowId);
    }
}
