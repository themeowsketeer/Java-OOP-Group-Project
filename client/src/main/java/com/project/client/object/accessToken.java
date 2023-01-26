package com.project.client.object;

public class accessToken {
    private static String token;

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        accessToken.token = token;
    }

    public accessToken() {
    }
}
