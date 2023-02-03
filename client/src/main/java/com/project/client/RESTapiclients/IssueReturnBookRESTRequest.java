package com.project.client.RESTapiclients;

import com.project.client.object.accessToken;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Class used to send REST API requests to the server.
 * Return JSON string format of issueBookInfo objects.
 *
 * @author Minh Duy
 */

public class IssueReturnBookRESTRequest {

    private static final HttpClient client = HttpClient.newHttpClient();

    /**
     * Default URL of the local server to request issueBookInfo objects.
     */
    private static final String baseUrl = "http://localhost:8080/api/issue";

    /**
     * GET method of an API call to receive information of every issueBookInfo objects in JSON string format.
     * @return JSON string format of every issueBookInfo objects.
     *         If there's no object to get, pointer NULL is used.
     * @apiNote Used method is GET. Header always includes access token for verification, otherwise
     * response will be NULL pointer and status code returns 401 - unauthorized access.
     */

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

    /**
     * GET method of an API call to receive information of
     * every issueBookInfo object consisting of same single userID value in JSON string format.
     * @param userID Declare ID of which issueBookInfo objects match the value.
     * @return JSON string format of every issueBookInfo object which userID matches the requested.
     *         If there's no object to get, pointer NULL is used.
     * @apiNote Used method is GET. Header always includes access token for verification, otherwise
     * response will be NULL pointer and status code returns 401 - unauthorized access.
     */

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

    /**
     * POST method of an API request to server for adding a new Book object to the database.
     * @param bookID Book object that the client requests to be sent to the server and included in
     *             the database.
     * @return JSON string format of newly created issueBookInfo object matching userID and bookID.
     * @apiNote Used method is POST. Header always includes access token for verification, otherwise
     * response will be NULL pointer and status code returns 401 - unauthorized access.
     */

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

    /**
     * POST method of an API request to server for adding issueBookInfo object a returned date of this
     * and removing object from the issueBook database.
     * @param borrowedID Id of issueBookInfo object that the client requests to be
     *               processed
     * @return JSON string format of newly created returnBookInfo object matching userID and bookID.
     * @apiNote Used method is POST. Header always includes access token for verification, otherwise
     * response will be NULL pointer and status code returns 401 - unauthorized access.
     */

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

    /**
     * GET method of an API call to receive information of every issueBookInfo
     * objects consisting of same single userID value in JSON string format
     * @return JSON string format of every returnBookInfo object with non-null returnAt attribute.
     *         If there's no object to get, pointer NULL is used.
     * @apiNote Used method is GET. Header always includes access token for verification, otherwise
     * response will be NULL pointer and status code returns 401 - unauthorized access.
     */

    public static HttpResponse<String> getAllReturnedBook() {
        try {
            String restUrl = baseUrl + "/return";
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
}
