package com.project.client.RESTapiclients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.client.object.User;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class UserRESTRequest {

    private static final HttpClient client = HttpClient.newHttpClient();

    private static final String authToken = "bla bla bla";

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final String baseUrl = "http://localhost:8080/api/users";

    public static HttpResponse<String> getUserByID(String userID) {
        try {
            String restUrl = baseUrl;
            if (!userID.equals("0"))
            {
                restUrl += "/" + userID;
            }
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .header("Accept", "application/json")
                    .header("Authorization","Bearer " + authToken)
                    .uri(URI.create(restUrl))
                    .build();
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Throwable e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
            return null;
        }
    }

    public static HttpResponse<String> addNewUser(User user) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(user)))
                    .header("Content-Type", "application/json")
                    .header("Authorization","Bearer " + authToken)
                    .uri(URI.create(baseUrl))
                    .build();
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Throwable e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
            return null;
        }
    }
}
