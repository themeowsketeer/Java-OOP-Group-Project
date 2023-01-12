package com.project.client.RESTapiclients;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.project.client.object.Book;

public class restapi_sample {

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static void addBook() {
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
        addBook();
    }
}
//    public static void main(String[] arg){
//        Book book_1 = new Book();
//        addBook(book_1);
//    }
//}