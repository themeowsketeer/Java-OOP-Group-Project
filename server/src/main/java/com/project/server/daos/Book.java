package com.project.server.daos;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    private String id;
    private String name;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Book_Author",
            joinColumns =
                    @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns =
                    @JoinColumn(name = "author_id", referencedColumnName = "id")
    )
    private Set<Author> authors = new HashSet<>();
    private Date releasedDate;
    private int edition;
    private Date placedAt;

    public void addAuthor(Author author) {
        authors.add(author);
    }
}
