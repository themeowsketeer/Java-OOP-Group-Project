package com.project.client.RESTapiclients;

import com.project.client.object.Author;
import com.project.client.object.Book;
import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.net.http.HttpResponse;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookRESTRequestTest {

    Set<Author> authorSet = new HashSet<>();
    Date now = new Date();
    private final Book sampleBook = new Book("3", "Wraith Arc", authorSet, 2013L, 5, 2, now);

    @Test
    @Order(1)
    void ensureThatPOSTMethodSent() {
        authorSet.add(new Author(4, "Inu Curry"));

        HttpResponse<String> responseTest = BookRESTRequest.addNewBook(sampleBook);
        if (responseTest == null) {
            fail("Response returns none!");
        } else {
            assertEquals(201, responseTest.statusCode());
        }
    }
    @Test
    @Order(2)
    void ensureThatGetBookAPICallReturnSuccessful() {
        HttpResponse<String> responseTest = BookRESTRequest.getBookByID("3");
        if (responseTest == null) {
            fail("Response returns none!");
        } else {
            assertEquals(200, responseTest.statusCode());
        }
    }

    @Test
    @Order(3)
    void ensureThatBookAPICallReturnAGoodJson() {
        try {
            HttpResponse<String> responseTest = BookRESTRequest.getBookByID("3");
            if (responseTest == null) {
                fail("Response returns none!");
            } else {
                JSONObject expected = new JSONObject();
                expected.put("id", "3");
                expected.put("name", "Wraith Arc");
                expected.put("edition", 5);
                expected.put("quantity", 2);
                expected.put("releasedYear", 2013);
                JSONAssert.assertEquals(expected.toString(), responseTest.body(), JSONCompareMode.LENIENT);
            }
        }
        catch (Throwable e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }

    @Test
    @Order(4)
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
    @Order(5)
    void ensureThatDELETEMethodSent() {
        HttpResponse<String> responseTest = BookRESTRequest.deleteBookByID(3);
        if (responseTest == null) {
            fail("Response returns none!");
        } else {
            assertEquals(204, responseTest.statusCode());
        }
    }
}