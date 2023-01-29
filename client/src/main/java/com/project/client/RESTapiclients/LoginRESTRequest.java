package com.project.client.RESTapiclients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.client.object.userAuth;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.project.client.object.accessToken;
public class LoginRESTRequest {

    private static final HttpClient client = HttpClient.newHttpClient();

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final String baseUrl = "http://localhost:8080/api/auth/";

    public static HttpResponse<String> loginRequest(userAuth userAuth) {
        try {
            String restUrl = baseUrl + "login";
            HttpRequest request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(userAuth)))
                    .header("Content-Type", "application/json")
                    .uri(URI.create(restUrl))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            //Save authorization information to a global variable for further usage of rest of APIs
            authTokenSetUp(response);
            return response;
        } catch (Throwable e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
            return null;
        }
    }

    public static HttpResponse<String> registerRequest(userAuth userAuth) {
        try {
            String restUrl = baseUrl + "register";
            HttpRequest request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(userAuth)))
                    .header("Content-Type", "application/json")
                    .uri(URI.create(restUrl))
                    .build();
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Throwable e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
            return null;
        }
    }
    public static void authTokenSetUp (HttpResponse<String> userInfo) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree(userInfo.body());

        accessToken.setToken(jsonNode.get("accessToken").asText());
        accessToken.setUserID(Long.valueOf(jsonNode.path("user").path("id").asText()));

        JsonNode roleArray = jsonNode.path("user").path("roles");
        JsonNode roleID = roleArray.get(0);
        accessToken.setRoleID(Long.parseLong(roleID.get("id").asText()));
    }
}
