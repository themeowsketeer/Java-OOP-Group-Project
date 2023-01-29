package com.project.client.RESTapiclients;

import com.project.client.object.accessToken;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class issueBookRESTRequest {

    private static final HttpClient client = HttpClient.newHttpClient();

    private static final String baseUrl = "http://localhost:8080/api/issue";

    public static HttpResponse<String> getAllIssuedBook() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .header("Accept", "application/json")
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

    public static HttpResponse<String> getAllIssuedBookOfUser(Long userID) {
        try {
            String restUrl = baseUrl + "/" + userID;
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

    public static HttpResponse<String> issueBookToUser(long userID, String bookID) {
        try {
            String restUrl = baseUrl + "?"
                    + "userid=" + userID + "&"
                    + "bookid=" + bookID;
            HttpRequest request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.noBody())
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

    public static HttpResponse<String> returnBookFromUser(Long borrowedID) {
        try {
            String restUrl = baseUrl + "/return?"
                    + "borrowid=" + borrowedID;
            HttpRequest request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.noBody())
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
