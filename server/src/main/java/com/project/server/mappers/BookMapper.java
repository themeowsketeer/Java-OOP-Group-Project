package com.project.server.mappers;

import com.project.server.daos.Author;
import com.project.server.daos.Book;
import com.project.server.dtos.AuthorDto;
import com.project.server.dtos.BookDto;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

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
}
