package com.project.client.RESTapiclients;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.net.http.HttpResponse;

class BookRESTRequestTest {
    @Test
    void ensureThatBookAPICallReturnStatusCode200() throws Exception {
        HttpResponse<String> response = BookRESTRequest.getJSON();
        assert response != null;
        assertEquals(200,response.statusCode());
    }
    @Test
    void ensureThatBookAPICallReturnAGoodJson() throws Exception {
        HttpResponse<String> response = BookRESTRequest.getJSON();
        ObjectMapper jsonTest_2 = new ObjectMapper();
        assertNotEquals(null,jsonTest_2.readTree(response.body()));
    }
}