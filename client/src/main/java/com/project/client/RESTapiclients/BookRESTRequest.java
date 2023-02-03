package com.project.client.RESTapiclients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.client.object.Book;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.project.client.object.accessToken;

/**
 * Class used to send REST API requests to the server. Return JSON string format of Book objects.
 *
 * @author Minh Duy
 */

public class BookRESTRequest {

    private static final HttpClient client = HttpClient.newHttpClient();

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Default URL of the local server to get Book objects
     */
    private static final String baseUrl = "http://localhost:8080/api/books";

    /**
     * GET method of an API call to receive information of Book objects in JSON string format
     * @param bookID Declare ID of which book the function requests.
     *               to receive information from. If ID is 0, client is requesting to
     *               have information of every Book objects.
     * @return JSON string format of Book object which ID is matched the requested.
     *               If ID 0 is used, every Book objects are included. If there's no
     *               object to get, pointer NULL is used.
     * @apiNote Used method is GET. Header always includes access token for verification, otherwise
     * response will be NULL pointer and status code returns 401 - unauthorized access.
     */

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

    /**
     * POST method of an API request to server for adding a new Book object to the database.
     * @param book Book object that the client requests to be sent to the server and included in
     *             the database.
     * @return JSON string format of sent Book object.
     * @apiNote Used method is POST. Header always includes access token for verification, otherwise
     * response will be NULL pointer and status code returns 401 - unauthorized access.
     */


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

    /**
     * PUT method of an API request to server for updating information of
     * an available Book object in the database.
     * @param bookID Used to verify which Book objects the client requests to update
     *               its information.
     * @param book Book object with updated information that the client requests to be sent
     *             to the server and replaced the available in the database.
     * @return JSON string format of new Book object which ID is matched the requested.
     *               If ID 0 is used, response code return 400.
     * @apiNote Used method is PUT. Header always includes access token for verification, otherwise
     * response will be NULL pointer and status code returns 401 - unauthorized access.
     */

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

    /**
     * DELETE method of an API call to server to delete
     * an available Book object in the database.
     * @param bookID Used to verify which Book objects the client requests to delete
     * @return NULL pointer. Status code 204 is used if successfully deleted.
     * @apiNote Used method is DELETE. Header always includes access token for verification, otherwise
     * response will be NULL pointer and status code returns 401 - unauthorized access.
     */

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