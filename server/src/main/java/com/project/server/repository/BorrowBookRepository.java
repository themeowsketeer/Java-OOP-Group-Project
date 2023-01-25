package com.project.server.repository;

import com.project.server.daos.BorrowBook;
import com.project.server.daos.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowBookRepository extends JpaRepository<BorrowBook, Long> {
    List<BorrowBook> findBorrowBooksByUser(UserEntity user);
}
