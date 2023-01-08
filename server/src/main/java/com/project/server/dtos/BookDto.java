package com.project.server.dtos;


import com.project.server.daos.Author;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class BookDto {
    private String id;
    private String name;
    private Set<Author> authors;
    private Date releasedDate;
    private int edition;
    private Date placedAt;

    public void addAuthor(Author author) {
        authors.add(author);
    }
}
