package com.project.server.daos;

import jakarta.persistence.*;
import lombok.*;

/**
 * The DAO for the author
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "author_name", nullable = false)
    private String name;
}
