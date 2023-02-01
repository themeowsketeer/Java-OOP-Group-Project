package com.project.server.dtos;


import com.project.server.daos.Author;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Set;

/**
 * The DTO for a book
 */
@Data
@Builder
public class BookDto {
    private String id;
    private String name;
    private Set<Author> authors;
    private Long releasedYear;
    private int edition;
    private Date placedAt;
    private long quantity;

    public void addAuthor(Author author) {
        authors.add(author);
    }
}
