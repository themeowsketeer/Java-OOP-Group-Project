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

/**
 * Class that provide method to log in to the application and store access token.
 * Token is required to access every function of the application. Otherwise, user
 * is forced to log out and log in for a new token registered.
 * @author Minh Duy
 */

public class LoginRESTRequest {

    private static final HttpClient client = HttpClient.newHttpClient();

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final String baseUrl = "https://library-java.fly.dev/api/auth/";

    /**
     * POST method of an API call to the server to verify user's credential and store access
     * token to the global variable, if verification is valid and successful.
     * @param userAuth Class packaged with username and password received from the
     *                 login UI of the application
     * @return JSON string format of access token and User object. If the server fails to verify
     * valid user, NULL pointer is used.
     * @apiNote Used method is POST.
     */

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
// Desolated. Once used for simple registration of Librarian-role user
//    public static HttpResponse<String> registerRequest(userAuth userAuth) {
//        try {
//            String restUrl = baseUrl + "register";
//            HttpRequest request = HttpRequest.newBuilder()
//                    .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(userAuth)))
//                    .header("Content-Type", "application/json")
//                    .uri(URI.create(restUrl))
//                    .build();
//            return client.send(request, HttpResponse.BodyHandlers.ofString());
//        } catch (Throwable e) {
//            System.out.println("Error: " + e);
//            e.printStackTrace();
//            return null;
//        }
//    }

    /**
     * Method used to assign accessToken object with valid access token granted from response of API call from
     * loginRequest method
     * @param userInfo The request received from the API call of loginRequest method to log into the application
     * @throws JsonProcessingException Exception thrown when the parameter is not in valid JSON string format,
     * which JSON parser cannot process the body string and cause the error.
     */
    public static void authTokenSetUp (HttpResponse<String> userInfo) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree(userInfo.body());

        accessToken.setToken(jsonNode.get("accessToken").asText());
        accessToken.setUserID(Long.valueOf(jsonNode.path("user").path("id").asText()));

        JsonNode roleArray = jsonNode.path("user").path("roles");
        JsonNode roleID = roleArray.get(0);
        accessToken.setRoleID(Long.parseLong(roleID.get("id").asText()));
    }
}
