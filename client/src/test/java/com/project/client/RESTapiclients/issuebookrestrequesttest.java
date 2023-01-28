package com.project.client.RESTapiclients;

import com.project.client.object.userAuth;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class issueBookRESTRequestTest {

    private final String bookID = "165";

    private final long userID = 1L;

    @Test
    @Order(1)
    void ensureThatLoginSuccessful() {
        userAuth sample = new userAuth("minhduy","123456");
        HttpResponse<String> responseTest = LoginRESTRequest.loginRequest(sample);
        if (responseTest == null) {
            fail("Response returns none!");
        } else {
            assertEquals(200, responseTest.statusCode());
        }
    }

    @Test
    @Order(2)
    void getAllIssuedBook() {
        HttpResponse<String> responseTest = issueBookRESTRequest.getAllIssuedBook();
        if (responseTest == null) {
            fail("Response returns none!");
        } else {
            assertEquals(200, responseTest.statusCode());
        }
    }

    @Test
    @Order(4)
    void getAllIssuedBookOfUser() {
        HttpResponse<String> responseTest = issueBookRESTRequest.getAllIssuedBookOfUser(userID);
        if (responseTest == null) {
            fail("Response returns none!");
        } else {
            assertEquals(200, responseTest.statusCode());
        }
    }

    @Test
    @Order(3)
    void issueBookToUser() {
        HttpResponse<String> responseTest = issueBookRESTRequest.issueBookToUser(bookID,userID);
        if (responseTest == null) {
            fail("Response returns none!");
        } else {
            assertEquals(200, responseTest.statusCode());
        }
    }

    @Test
    @Order(5)
    void returnBookFromUser() {
        HttpResponse<String> responseTest = issueBookRESTRequest.returnBookFromUser(2L);
        if (responseTest == null) {
            fail("Response returns none!");
        } else {
            assertEquals(200, responseTest.statusCode());
        }
    }
}