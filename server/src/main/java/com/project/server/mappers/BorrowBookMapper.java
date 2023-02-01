package com.project.server.mappers;

import com.project.server.daos.BorrowBook;
import com.project.server.dtos.BorrowDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * The mapper between the DAO and the DTO for a borrowed book
 */
@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {BookMapper.class, UserMapper.class}
)
public interface BorrowBookMapper {
    BorrowDto map(BorrowBook borrowBook);
    BorrowBook map(BorrowDto borrowBook);
    List<BorrowDto> map(List<BorrowBook> borrowBooks);
}
