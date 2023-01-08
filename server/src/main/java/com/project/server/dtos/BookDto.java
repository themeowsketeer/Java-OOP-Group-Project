package com.project.server.dtos;


import com.project.server.daos.Author;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Year;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
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
