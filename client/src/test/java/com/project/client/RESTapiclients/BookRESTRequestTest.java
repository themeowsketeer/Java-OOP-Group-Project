package com.project.client.RESTapiclients;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.fail;

class BookRESTRequestTest {
    @Test
    void ensureThatBookAPICallReturnStatusCode200() throws Exception {
        String url = "http://localhost:8080/api/books";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        fail("FIXME");
    }
}