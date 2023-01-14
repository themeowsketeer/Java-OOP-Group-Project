package com.project.client.RESTapiclients;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

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
        if (response == null)
        {
            fail("Response returns none!");
        }
        else {
            assertEquals(200, response.statusCode());
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
        assertNotEquals(null,objectMapper.readTree(response.body()));
    }
}