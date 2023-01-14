package com.project.client.RESTapiclients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.client.object.Book;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class BookRESTRequest {

    private static final HttpClient client = HttpClient.newHttpClient();

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void getJSON() {
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
        } catch (Throwable e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }

    public static void addJSON(Book book) {
        try {
            String restUrl =
                    "http://localhost:8080/api/books";
            HttpRequest request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(book.toString()))
                    .header("Content-Type", "application/json")
                    .uri(URI.create(restUrl))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Throwable e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }

    public static void deleteJSON(String id) {
        try {
            String restUrl =
                    "http://localhost:8080/api/books" + id;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .DELETE()
                    .header("Content-Type", "application/json")
                    .uri(URI.create(restUrl))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Throwable e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }
}