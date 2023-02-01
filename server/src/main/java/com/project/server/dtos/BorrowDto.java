package com.project.server.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * The DTO for a borrowed book
 */
@Data
@AllArgsConstructor
@Builder
public class BorrowDto {
    private long id;
    private Date issuedAt;
    private BookDto book;
    private UserDto user;
    private Date returnedAt;
}
