package com.project.server.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.server.daos.Author;
import com.project.server.dtos.BookDto;
import com.project.server.repository.BookRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.exparity.hamcrest.date.DateMatchers.sameDay;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ObjectMapper objectMapper;

//    @BeforeEach
//    void setup() {
//        bookRepository.deleteAll();
//    }
//
//    @Test
//        public void givenBookDTO_whenCreateBook_thenReturnSavedBook() throws Exception {
//        // given - precondition
//        Set<Author> authors = new HashSet<>();
//        authors.add(Author.builder().id(1L).name("Haruki").build());
//        authors.add(Author.builder().id(2L).name("Helen").build());
//        Date now = new Date();
//        BookDto book = BookDto.builder()
//                .id("10")
//                .edition(1)
//                .releasedYear(2012L)
//                .quantity(20)
//                .placedAt(now)
//                .authors(authors)
//                .name("Norwegian Woods")
//                .build();
//
//        // when - result action to test
//        ResultActions response = mockMvc.perform(post("/api/books")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(book)));
//
//        // then - verify the out with assert statement
//        response.andDo(print())
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id",
//                        is(book.getId())))
//                .andExpect(jsonPath("$.edition",
//                        is(book.getEdition())))
//                .andExpect(jsonPath("$.releasedYear",
//                        is(book.getReleasedYear()), Long.class))
//                .andExpect(jsonPath("$.quantity",
//                        is(book.getQuantity()), Long.class))
//                .andExpect(jsonPath("$.authors",
//                        hasSize(2)))
//                .andExpect(jsonPath("$.name",
//                        is(book.getName())));
//
//    }
}
