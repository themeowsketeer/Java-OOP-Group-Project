package com.project.client.RESTapiclients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.client.object.Book;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.project.client.object.accessToken;

public class BookRESTRequest {

    private static final HttpClient client = HttpClient.newHttpClient();

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final String baseUrl = "http://localhost:8080/api/books";

    public static HttpResponse<String> getBookByID(String bookID) {
        try {
            String restUrl = baseUrl;
            if (!bookID.equals("0"))
            {
                restUrl += "/" + bookID;
            }
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .header("Accept", "application/json")
                    .header("Authorization","Bearer " + accessToken.getToken())
                    .uri(URI.create(restUrl))
                    .build();
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Throwable e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
            return null;
        }
    }

    public static HttpResponse<String> addNewBook(Book book) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(book)))
                    .header("Content-Type", "application/json")
                    .header("Authorization","Bearer " + accessToken.getToken())
                    .uri(URI.create(baseUrl))
                    .build();
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Throwable e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
            return null;
        }
    }

    public static HttpResponse<String> updateBookByID(int bookID, Book book) {
        try {
            String restUrl = baseUrl + "/" + bookID;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .PUT(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(book)))
                    .header("Content-Type", "application/json")
                    .header("Authorization","Bearer " + accessToken.getToken())
                    .uri(URI.create(restUrl))
                    .build();
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Throwable e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
            return null;
        }
    }

    public static HttpResponse<String> deleteBookByID(int bookID) {
        try {
            String restUrl = baseUrl + "/" + bookID;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .DELETE()
                    .header("Content-Type", "application/json")
                    .header("Authorization","Bearer " + accessToken.getToken())
                    .uri(URI.create(restUrl))
                    .build();
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Throwable e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
            return null;
        }
    }
}