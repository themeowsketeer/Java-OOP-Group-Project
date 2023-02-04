package com.project.client.RESTapiclients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.client.object.User;
import com.project.client.object.accessToken;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Class used to send REST API requests to the server. Return JSON string format of User objects.
 *
 * @author Minh Duy
 */

public class UserRESTRequest {

    private static final HttpClient client = HttpClient.newHttpClient();

    private static final String authToken = accessToken.getToken();

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Default URL of the local server to get User objects
     */
    private static final String baseUrl = "https://library-java.fly.dev/api/users";

    /**
     * GET method of an API call to receive information of User objects in JSON string format
     * @param userID Declare ID of which User the function requests
     *               to receive information from. If ID is 0, client requests to
     *               have information of every User objects.
     * @return JSON string format of User object which ID is matched the requested.
     *               If ID 0 is used, every User objects are included. If there's no
     *               object to get, pointer NULL is used
     * @apiNote Used method is GET. Header always includes access token for verification, otherwise
     * response will be NULL pointer and status code returns 401 - unauthorized access.
     */

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

    /**
     * POST method of an API request to server for adding a new User object to the database
     * @param user User object that the client requests to be sent to the server and included in
     *             the database
     * @return JSON string format of sent User object.
     * @apiNote Used method is POST. Header always includes access token for verification, otherwise
     * response will be NULL pointer and status code returns 401 - unauthorized access.
     */

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
