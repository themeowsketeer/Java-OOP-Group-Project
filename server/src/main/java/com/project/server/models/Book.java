package com.project.server.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    private String name;
    private String author;
    private int publishedYear;
    private int edition;
}
