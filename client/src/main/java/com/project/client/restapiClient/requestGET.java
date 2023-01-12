package com.project.client.restapiClient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.project.client.object.Book;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class requestGET {

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static void getJSON() {
        try {
            String restUrl =
                    "http://localhost:8080/api/books"
                            ;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .header("accept","application/json")
                    .uri(URI.create(restUrl))
                    .build();
            HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            System.out.println(response.body());
        } catch (Throwable e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }
    public static void main(String[] arg){
        getJSON();
    }
}