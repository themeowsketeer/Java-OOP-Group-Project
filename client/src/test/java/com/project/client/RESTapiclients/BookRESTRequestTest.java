package com.project.client.RESTapiclients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.client.object.Author;
import com.project.client.object.Book;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.Set;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class BookRESTRequestTest {
    private final String restUrl =
            "http://localhost:8080/api";
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void ensureThatBookAPICallReturnStatusCode200() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("Accept", "application/json")
                .uri(URI.create(restUrl + "/books"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response == null) {
            fail("Response returns none!");
        } else {
            assertEquals(200, response.statusCode());
            System.out.println(response.body());
        }
    }

    @Test
    void ensureThatBookAPICallReturnAGoodJson() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("Accept", "application/json")
                .uri(URI.create(restUrl + "/books"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertNotEquals(null, objectMapper.readTree(response.body()));
    }
    @Test
    void ensureThatBookAPIPOSTmethodSent() throws Exception {
        Set<Author> authorSet = new HashSet<>();
        Date now = new Date();
        authorSet.add(new Author(4,"Inu Curry"));
        Book sampleBook = new Book("3","Wraith Arc",authorSet,2013L,5,2,now);
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(sampleBook)))
                .header("Content-Type", "application/json")
                .uri(URI.create(restUrl + "/books"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(201, response.statusCode());
        System.out.println(response.body());
    }
    @Test
    void ensureThatBookAPIDELETEmethodSent() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .DELETE()
                .header("Accept", "application/json")
                .uri(URI.create(restUrl + "/books/" + 3))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(204, response.statusCode());
    }
}