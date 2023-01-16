package com.project.client.RESTapiclients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.client.object.Author;
import com.project.client.object.Book;
import org.junit.jupiter.api.Test;

import java.net.http.HttpResponse;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BookRESTRequestTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void ensureThatBookAPICallReturnStatusCode200() {
        HttpResponse<String> responseTest = BookRESTRequest.getBookByID(1);
        if (responseTest == null) {
            fail("Response returns none!");
        } else {
            assertEquals(200, responseTest.statusCode());
            System.out.println(responseTest.body());
        }
    }

    @Test
    void ensureThatBookAPICallReturnAGoodJson() throws JsonProcessingException {
        HttpResponse<String> responseTest = BookRESTRequest.getBookByID(2);
        if (responseTest == null) {
            fail("Response returns none!");
        } else {
            assertNotEquals(null, objectMapper.readTree(responseTest.body()));
        }
    }
    @Test
    void ensureThatPOSTandPUTmethodSent() {
        Set<Author> authorSet = new HashSet<>();
        Date now = new Date();
        authorSet.add(new Author(4,"Inu Curry"));
        Book sampleBook = new Book("3","Wraith Arc",authorSet,2013L,5,2,now);

        HttpResponse<String> responseTest_1 = BookRESTRequest.addnewBook(sampleBook);
        if (responseTest_1 == null) {
            fail("Response returns none!");
        } else {
            assertEquals(201, responseTest_1.statusCode());
            System.out.println(responseTest_1.body());
        }

        sampleBook.setName("Madoka Magica");

        HttpResponse<String> responseTest_2 = BookRESTRequest.updateBookByID(3,sampleBook);
        if (responseTest_2 == null) {
            fail("Response returns none!");
        } else {
            assertEquals(200, responseTest_2.statusCode());
            System.out.println(responseTest_2.body());
        }
    }
    @Test
    void ensureThatDELETEmethodSent() {
        HttpResponse<String> responseTest = BookRESTRequest.deleteBookByID(3);
        if (responseTest == null) {
            fail("Response returns none!");
        } else {
            assertEquals(204, responseTest.statusCode());
        }
    }
}