package com.project.server.mappers;

import com.project.server.daos.Author;
import com.project.server.daos.Book;
import com.project.server.dtos.AuthorDto;
import com.project.server.dtos.BookDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Set;

/**
 * The mapper between the DAO and the DTO for a book
 */
@Mapper(
        componentModel = "spring"
)
public interface BookMapper {
    BookDto map(Book book);
    AuthorDto map(Author author);
    Set<AuthorDto> map(Set<Author> authors);
    Book map(BookDto book);
    Author map(AuthorDto author);
    List<BookDto> map(List<Book> books);

    @Mapping(ignore = true, target = "id")
    void mapTo(@MappingTarget Book entity, BookDto book);
}
