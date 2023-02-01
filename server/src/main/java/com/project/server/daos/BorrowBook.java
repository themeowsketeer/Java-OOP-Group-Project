package com.project.server.daos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * The DAO for a borrow book
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BorrowBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "issued_at")
    private Date issuedAt = new Date();

    @Column(name = "returned_at")
    private Date returnedAt;
}
