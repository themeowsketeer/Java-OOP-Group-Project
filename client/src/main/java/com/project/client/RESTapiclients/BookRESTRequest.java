package com.project.client.RESTapiclients;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.project.client.object.Book;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class BookRESTRequest {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static HttpResponse<String> getJSON() {
        try {
            String restUrl =
                    "http://localhost:8080/api/books";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .header("Accept", "application/json")
                    .uri(URI.create(restUrl))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return response;
        } catch (Throwable e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
            return null;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static HttpResponse<String> addJSON(Book book) {
        try {
            String restUrl =
                    "http://localhost:8080/api/books";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(book.toString()))
                    .header("Content-Type", "application/json")
                    .uri(URI.create(restUrl))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response;
        } catch (Throwable e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
            return null;
        }
    }
}