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
@Builder
public class Book {
    @Id
    private String id;
    private String name;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "Book_Author",
            joinColumns =
                    @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns =
                    @JoinColumn(name = "author_id", referencedColumnName = "id")
    )
    private Set<Author> authors = new HashSet<>();
    private Long releasedYear;
    private int edition;
//    @Temporal(TemporalType.DATE)
    private Date placedAt;
    private Long quantity;

    public void addAuthor(Author author) {
        authors.add(author);
    }
}
