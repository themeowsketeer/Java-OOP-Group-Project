package com.project.client.RESTapiclients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.client.object.Author;
import com.project.client.object.Book;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.net.http.HttpResponse;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BookRESTRequestTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    Set<Author> authorSet = new HashSet<>();
    Date now = new Date();

    private final Book sampleBook = new Book("3", "Wraith Arc", authorSet, 2013L, 5, 2, now);

//    @Test
//    void ensureThatServerIsRunning() {
//        ServerApplication();
//    }

    @Test
    void ensureThatGetBookAPICallReturnSuccessful() throws JSONException {
        HttpResponse<String> responseTest = BookRESTRequest.getBookByID(1);
        if (responseTest == null) {
            fail("Response returns none!");
        } else {
            assertEquals(200, responseTest.statusCode());
            JSONObject actual = new JSONObject();
            actual.put("id", 3);
            actual.put("name", "Wraith Arc");
            actual.put("edition", 5);
            actual.put("quantity", 2);
            actual.put("releasedYear", 2013L);
            actual.put("placedAt", now);
            System.out.println(responseTest.body());
        }
    }

    @Test
    void ensureThatPOSTMethodSent() {
        authorSet.add(new Author(4, "Inu Curry"));

        HttpResponse<String> responseTest = BookRESTRequest.addNewBook(sampleBook);
        if (responseTest == null) {
            fail("Response returns none!");
        } else {
            assertEquals(201, responseTest.statusCode());
            System.out.println(responseTest.body());
        }
    }

    @Test
    void ensureThatBookAPICallReturnAGoodJson() throws JsonProcessingException {
        HttpResponse<String> responseTest = BookRESTRequest.getBookByID(3);
        if (responseTest == null) {
            fail("Response returns none!");
        } else {
            assertNotEquals(null, objectMapper.readTree(responseTest.body()));
        }
    }

    @Test
    void ensureThatPUTMethodSent() {
        sampleBook.setName("Madoka Magica");
        authorSet.add(new Author(5, "Gen Doka"));

        HttpResponse<String> responseTest = BookRESTRequest.updateBookByID(3,sampleBook);
        if (responseTest == null) {
            fail("Response returns none!");
        } else {
            assertEquals(200, responseTest.statusCode());
            System.out.println(responseTest.body());
        }
    }
    @Test
    void ensureThatDELETEMethodSent() {
        HttpResponse<String> responseTest = BookRESTRequest.deleteBookByID(3);
        if (responseTest == null) {
            fail("Response returns none!");
        } else {
            assertEquals(204, responseTest.statusCode());
        }
    }
}