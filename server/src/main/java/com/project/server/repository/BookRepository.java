package com.project.server.repository;

import com.project.server.daos.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The repository interface for accessing the books table
 */
@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    Optional<Book> findBookByName(String name);
}
